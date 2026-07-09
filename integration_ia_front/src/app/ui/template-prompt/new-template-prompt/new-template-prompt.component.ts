import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Variables } from 'app/class/Enums/Variables';
import { EnumVo } from 'app/class/EnumVo';
import { TemplatePrompt } from 'app/class/TemplatePrompt';
import { EnumService } from 'app/services/Enum.service';
import { TemplatePromptService } from 'app/services/template-prompt.service';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-template-prompt',
  templateUrl: './new-template-prompt.component.html',
  styleUrls: ['./new-template-prompt.component.css']
})


      export class NewTemplatePromptComponent implements OnInit {

        templatePrompt: TemplatePrompt;
        update: boolean = false;
        error: boolean = false;
        d: any = {};
        errorMessage: string = '';
        userConnected: any = {};
        enumVar: EnumVo[] = [];
        selectedVariables: string[] = []; // Stocker les variables sélectionnées
        description: string = '';
      
        // FormControl pour la validation de l'email
        emailFormControl = new FormControl('', [Validators.required, Validators.email]);
      
        constructor(
          @Inject(MAT_DIALOG_DATA) public data: TemplatePrompt,
          private templatePromptService: TemplatePromptService,
          private enumService: EnumService,
          private tokenService: TokenStorageService,
          private _snackBar: MatSnackBar
        ) {
          this.templatePrompt = new TemplatePrompt();
        }
      
        ngOnInit(): void {
          // Récupération de l'utilisateur connecté
          this.userConnected = this.tokenService.getUser();
          console.log("Utilisateur connecté:", this.userConnected.codeUtilisateur);
      
          // Charger les variables disponibles
          this.enumService.findAllVariables().subscribe(result => {
            this.enumVar = result.payload;
            console.log("Variables disponibles:", this.enumVar);
          });
      
          // Si modification, charger les données existantes
          if (this.data) {
            this.update = true;
            this.templatePrompt = this.data;
            this.selectedVariables = this.templatePrompt.variables 
              ? this.templatePrompt.variables.split(';').filter(v => v) 
              : [];
            console.log("Variables sélectionnées pour modification:", this.selectedVariables);
          }
        }
      
        // Gestion de la sélection/déselection des variables
        onCheckboxChange(variable: EnumVo, isChecked: boolean) {
          const variableValue = variable.value;  // Utiliser la valeur de la variable
      
          if (isChecked) {
            if (!this.selectedVariables.includes(variableValue)) {
              this.selectedVariables.push(variableValue);
              console.log(`Variable cochée: ${variableValue}`);
            }
          } else {
            this.selectedVariables = this.selectedVariables.filter(v => v !== variableValue);
            console.log(`Variable décochée: ${variableValue}`);
          }
      
          // Mettre à jour la chaîne 'variables' de templatePrompt
          this.templatePrompt.variables = this.constructElementString();
        }
      
        // Concaténation des variables dans une chaîne
        constructElementString(): string {
          const variablesString = this.selectedVariables.join(';') + ';';
          console.log("Chaîne des variables:", variablesString);
          return variablesString;
        }
      
        // Remplacement des placeholders par les valeurs réelles des utilisateurs
replaceVariablesInDescription(): void {
  let finalPrompt = this.templatePrompt.description;

  // Remplacer chaque variable (placeholder) dans la description par sa valeur réelle
  this.selectedVariables.forEach(variable => {
    finalPrompt = this.replacePlaceholderWithRealValue(finalPrompt, variable);
  });

  // Mettre à jour la description du templatePrompt avec la description finale
  this.templatePrompt.description = finalPrompt;
  console.log("Description après remplacement des variables:", finalPrompt);
}

// Remplace les placeholders {{VARIABLE}} par leurs valeurs réelles
replacePlaceholderWithRealValue(prompt: string, variable: string): string {
  let variableValue: string;

  // Association des placeholders avec les informations utilisateurs
  switch (variable) {
      case 'VARIABLE1':
          variableValue = this.userConnected.typeUser || '';
          break;
      case 'VARIABLE2':
          variableValue = this.userConnected.filiere || '';
          break;
      case 'VARIABLE3':
          variableValue = this.userConnected.matiere || '';
          break;
      case 'VARIABLE4':
          variableValue = this.userConnected.notes || '';
          break;
      case 'VARIABLE5':
          variableValue = this.userConnected.nameMetier || '';
          break;
      case 'VARIABLE6':
          variableValue = this.userConnected.niveauAcces || '';
          break;
      case 'VARIABLE7':
          variableValue = this.userConnected.descriptionMetier || '';
          break;
      case 'VARIABLE8':
          variableValue = this.userConnected.statistique || '';
          break;
      default:
          variableValue = '';
  }

  // Remplacer le placeholder {{VARIABLE}} par la valeur réelle
  const placeholder = `{{${variable}}}`;
  return prompt.replace(new RegExp(placeholder, 'g'), variableValue);
}

      
        onSubmit(): void {
          // Mise à jour des variables sélectionnées
          this.templatePrompt.variables = this.constructElementString();
      
          // Lors de la création du template, on n'effectue pas le remplacement des placeholders
          if (this.verifierSaisie()) {
              if (this.update) {
                  // Mise à jour du template
                  this.templatePrompt.updateDate = new Date();
                  this.templatePrompt.updaterCode = this.userConnected.codeUtilisateur;
                  this.templatePromptService.update(this.templatePrompt).subscribe(result => {
                      this.handleResult(result);
                  });
              } else {
                  // Enregistrement du nouveau template
                  this.templatePrompt.creationDate = new Date();
                  this.templatePrompt.deleted = false;
                  this.templatePrompt.creatorCode = this.userConnected.codeUtilisateur;
                  this.templatePromptService.save(this.templatePrompt).subscribe(result => {
                      this.handleResult(result);
                  });
              }
          }
      }
      
      
        // Vérifier que tous les champs obligatoires sont remplis
        verifierSaisie(): boolean {
          if (!this.templatePrompt.variables || !this.templatePrompt.description) {
            this.openSnackBar('Veuillez remplir les champs obligatoires (*) !', 'Ok');
            return false;
          }
          return true;
        }
      
        // Afficher un message de succès ou d'erreur
        handleResult(result: any): void {
          this.d = result;
          if (this.d.status === 'OK') {
            this.alerter('Succès !', 'success', this.d.message);
          } else {
            this.alerter('Erreur !', 'error', this.d.message);
          }
        }
      
        openSnackBar(message: string, action: string) {
          this._snackBar.open(message, action, { duration: 3000 });
        }

        closeError(): void {
          this.error = false;
          this.errorMessage = '';
        }
      
        // Alerte via Swal
        alerter(message: string, icon: any, texte: string) {
          Swal.fire({
            title: message,
            text: texte,
            icon: icon,
            showCancelButton: false,
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
          }).then(result => {
            if (result.isConfirmed) {
              location.reload();
            }
          });
        }
      }
      
 
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import { CredentialUpdateVo } from './../../class/CredentialUpdateVo';

import { Component, Inject, OnInit } from '@angular/core';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PersonneService } from 'app/user-module/service/personne.service';
import { UtilisateurService } from 'app/user-module/service/utilisateur.service';

@Component({
  selector: 'app-mon-compte',
  templateUrl: './mon-compte.component.html',
  styleUrls: ['./mon-compte.component.scss']
})
export class MonCompteComponent implements OnInit {

  utilisateur: Utilisateur
  credentialUpdateVo: CredentialUpdateVo
  confirmPassword: string = "";
  userConnected: any = {};
  d: any = [{}];

  error: boolean = false;
  errorMessage: string = '';
  errorUser: boolean = false;
  errorMessageUser: string = '';

  constructor(public dialog: MatDialog,
    private personneService: PersonneService,
    private utilisateurService: UtilisateurService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private tokenService: TokenStorageService) {
    this.utilisateur = new Utilisateur()
    this.credentialUpdateVo = new CredentialUpdateVo
  }

  ngOnInit() {
    this.userConnected = this.tokenService.getUser();
    console.log("USERCODE", this.userConnected.codeUtilisateur);
  }

  onSubmit(): void {
    if (this.credentialUpdateVo.newPassword === this.confirmPassword) {
      //Passer le code utilisateur das username
      this.credentialUpdateVo.username = this.userConnected.codeUtilisateur;
      console.log("CREDENTIAL VO", this.credentialUpdateVo)
      this.utilisateurService.updatePassWord(this.credentialUpdateVo).subscribe(result =>{
        this.d = result;
        console.log(this.d);
        
        if(this.d.status === "OK"){
          console.log("passed");
          this.goHome();
        } else {
          console.log("Echec");
          this.error = true;
          this.errorMessage = "Echec de la modification";
        }
      });
    } else {
      //Show erreur
      console.log("PWD incompatible");
      this.error = true;
      this.errorMessage = "Erreur lors de la confirmation du mot de passe";
    }
  }

  closeError() {
    this.error = false;
    this.errorMessage = "";
  }

  goHome() {
    location.reload();
  }
}

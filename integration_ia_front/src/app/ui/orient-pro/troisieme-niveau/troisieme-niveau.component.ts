import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { CareerRecommendation } from 'app/class/CareerRecommendation';
import { Cinematique } from 'app/class/Cinematique';
import { Parametre } from 'app/class/Parametre';
import { CinematiqueService } from 'app/services/cinematique.service';
import { ParametreService } from 'app/services/parametre.service';
import { TemplatePromptService } from 'app/services/template-prompt.service'; // Import du service
import { TemplatePrompt } from 'app/class/TemplatePrompt'; // Import du modèle
import { MTResponse } from 'app/user-module/class/MTResponse';
import { MatSelect } from '@angular/material/select';
import { NoteMatriculeService } from 'app/services/note-matricule.service';


@Component({
  selector: 'app-troisieme-niveau',
  templateUrl: './troisieme-niveau.component.html',
  styleUrls: ['./troisieme-niveau.component.css']
})

export class TroisiemeNiveauComponent implements OnInit {
  @ViewChild('matSelect') matSelect: MatSelect; // Référence au mat-select
  selectedItems: Cinematique[] = [];
  maxSelection = 100;
  cinematiques: Cinematique[] = [];
  parametres: Parametre[] = [];
  selectedParametre: string;
  careerOptions: CareerRecommendation[] = [];  // Tableau de recommandations
  loading = false;
  message = '';
  templatePrompt: TemplatePrompt | null = null; // Pour stocker le template
  templatePromptDescription: string | null = null; // Pour stocker uniquement la description
  templatePromptForSelect: string | null = null; // Pour stocker uniquement la description
  templatePromptForSelectNote: string | null = null; // Pour stocker uniquement la description
  recommendations: any[] = [];
  niveaux: any[] = [];
  //notes: any[] = []; // variable pour le findAll
  notes: { semestre: string; module: string; libelle: string; valeur: string }[] = [];
  niveauSelectionne: string | null = null;
  //noteSelectionne: string | null = null;
  //notesSelectionnees: any[] = []; // Tableau pour stocker les notes sélectionnées
  selectedModules: string[] = [];
  selectedNotes: string[] = [];
  allNotesSelected: boolean = false;
  allSelected: boolean = false; // Variable pour suivre l'état de "Tout cocher"
  notesSelectionnees: any[] = []; // Variable pour stocker les notes sélectionnées
  notesDisponibles: any[] = [ /* tes notes ici */ ]; // Ta liste de notes

  

  constructor(
    private cinematiqueService: CinematiqueService,
    private http: HttpClient,
    private parametreService: ParametreService,
    private notesService: NoteMatriculeService,   //notes stocké lors de la connexion par matricule 
    private templatePromptService: TemplatePromptService // Injection du service
  ) {}

  ngOnInit(): void {

    // Récupération des cinématiques
    this.cinematiqueService.findAll().subscribe(response => {
      if (response && response.payload) {
        this.cinematiques = response.payload;
      } else {
        console.error('Erreur: Données non disponibles');
      }
    }, error => {
      console.error('Erreur de récupération des données', error);
    });


    // Récupération des paramètres
    this.parametreService.findAll().subscribe(response => {
      if (response && response.payload) {
        this.parametres = response.payload;
      } else {
        console.error('Erreur: Données non disponibles');
      }
    }, error => {
      console.error('Erreur de récupération des paramètres', error);
    });

    // Appel du service pour récupérer les niveaux
    this.parametreService.findAll().subscribe(
      (response: MTResponse<any[]>) => {
        if (response.status === 'OK' && response.payload) {
          this.niveaux = response.payload.filter(niveau => !niveau.deleted);  // Filtre les niveaux non supprimés
        } else {
          console.error('Erreur dans la réponse de l\'API:', response.message);
        }
      },
      (error) => {
        console.error('Erreur lors de la récupération des niveaux:', error);
      }
    );


    // Appel du service pour récupérer les notes
    /*this.parametreService.findAllNote().subscribe(
      (response: MTResponse<any[]>) => {
        if (response.status === 'OK' && response.payload) {
          this.notes = response.payload.filter(note => !note.deleted);  // Filtre les notes non supprimés
        } else {
          console.error('Erreur dans la réponse de l\'API:', response.message);
        }
      },
      (error) => {
        console.error('Erreur lors de la récupération des notes:', error);
      }
    ); */
    // Récupérer les notes à partir du service
    this.notes = this.notesService.getNotes().filter(note => note.valeur !== ''); // Filtrer les notes non supprimées ou sans valeur



    //Recuperer deux template de prompt
    // Récupération du premier TemplatePrompt
  this.templatePromptService.findByCode(/*'IIATPROM6180170051616'*/'IAATPROM3201211519018117').subscribe(
    (response: any) => {
      console.log('Premier Template récupéré:', response);

      // Vérification si le payload du premier template est disponible
      if (response && response.payload && response.payload.length > 0) {
        const firstTemplatePrompt = response.payload[0];  // Accéder au premier élément du tableau payload

        if (firstTemplatePrompt && firstTemplatePrompt.description) {
          this.templatePromptDescription = firstTemplatePrompt.description;  // Stocke la description du premier template
          console.log('Description du premier template:', this.templatePromptDescription);
        } else {
          console.error('Le premier template n\'a pas de description.');
        }
      } else {
        console.error('Le payload du premier template est vide.');
      }
    },
    error => {
      console.error('Erreur lors de la récupération du premier template:', error);
    }
  );

  // Récupération du second TemplatePrompt niveau IAATPROM19141917217718170
  this.templatePromptService.findByCode('IAATPROM19141917217718170').subscribe(
    (response: any) => {
      console.log('Deuxième Template récupéré:', response);

      // Vérification si le payload du second template est disponible
      if (response && response.payload && response.payload.length > 0) {
        const secondTemplatePrompt = response.payload[0];  // Accéder au premier élément du tableau payload

        if (secondTemplatePrompt && secondTemplatePrompt.description) {
          this.templatePromptForSelect = secondTemplatePrompt.description;  // Stocke la description du deuxième template
          console.log('Description du deuxième template (pour le select):', this.templatePromptForSelect);
        } else {
          console.error('Le deuxième template n\'a pas de description.');
        }
      } else {
        console.error('Le payload du deuxième template est vide.');
      }
    },
    error => {
      console.error('Erreur lors de la récupération du deuxième template:', error);
    }
  );

  // Récupération du TemplatePrompt pour notes-modules
  this.templatePromptService.findByCode(/*'IIATPROM13212114018134129'*/'IAATPROM1874212210232624').subscribe(
    (response: any) => {
      console.log('TemplatePrompt pour notes-modules récupéré:', response);

      // Vérification si le payload du second template est disponible
      if (response && response.payload && response.payload.length > 0) {
        const secondTemplatePrompt = response.payload[0];  // Accéder au premier élément du tableau payload

        if (secondTemplatePrompt && secondTemplatePrompt.description) {
          this.templatePromptForSelectNote = secondTemplatePrompt.description;  // Stocke la description du deuxième template
          console.log('Description du TemplatePrompt pour notes-modules:', this.templatePromptForSelectNote);
        } else {
          console.error('Le TemplatePrompt pour notes-modules n\'a pas de description.');
        }
      } else {
        console.error('Le payload du TemplatePrompt pour notes-modules est vide.');
      }
    },
    error => {
      console.error('Erreur lors de la récupération du TemplatePrompt pour notes-modules:', error);
    }
  );
  }



    // Gestion de la sélection du niveau d'étude
  onEducationLevelChange(event: any): void {
    this.niveauSelectionne = event.target.value;
    console.log('Niveau sélectionné:', this.niveauSelectionne);

    // Appel de la fonction pour remplacer la variable dans le templatePrompt
    if (this.templatePromptForSelect && this.niveauSelectionne) {
      this.replaceNiveauInTemplate(this.niveauSelectionne);
      
    } else {
      console.error("Le template ou le niveau sélectionné n'est pas disponible.");
    }
  }

  // Remplacer la variable {{niveauSelectionne}} dans le templatePrompt
  replaceNiveauInTemplate(niveau: string): void {
    if (this.templatePromptForSelect) {
      // Utilisation de replace pour remplacer {{niveauSelectionne}} par le niveau sélectionné
      const finalPrompt = this.templatePromptForSelect.replace(/{{niveauSelectionne}}/g, niveau);
      console.log('Template après remplacement:', finalPrompt);

      // Vous pouvez maintenant envoyer ce prompt à l'IA ou l'utiliser ailleurs
      // Appeler la fonction pour soumettre le template après remplacement
      this.submitNiveauTemplate(finalPrompt);
    } else {
      console.error('Le templatePrompt est vide ou non récupéré.');
    }
  }

  submitNiveauTemplate(finalPrompt: string): void {
    this.loading = true;
  
    // Envoi de la requête HTTP avec le template mis à jour
    this.http.post('http://localhost:8181/career/generateCareerRecommendationsByTemplate', finalPrompt, {
      headers: { 'Content-Type': 'text/plain' }
    })
    .subscribe(
      (response: CareerRecommendation[]) => {
        // Traiter la réponse comme un tableau de recommandations de carrière
        this.careerOptions = response;
        this.loading = false;  // Arrêter le chargement
      },
      (error: any) => {
        this.handleError(error);  // Gérer les erreurs
        this.loading = false;  // Arrêter le chargement
      }
    );
  }
  



  // Ajoute ces fonctions dans ton composant TypeScript pour gérer les changements de chaque champ
onFiliereChange(event: any) {
  const selectedFiliere = event.target.value;
  console.log('Filière sélectionnée:', selectedFiliere);
}

onModuleChange(event: any) {
  const selectedModule = event.target.value;
  console.log('Module sélectionné:', selectedModule);
}

//fonction lorsqu'on utilise le selct notes
/*onNotesChange(event: any) {
  this.noteSelectionne = event.target.value;
    console.log('Note sélectionné:', this.noteSelectionne);

    // Appel de la fonction pour remplacer la variable dans le templatePrompt
    if (this.templatePromptForSelectNote && this.noteSelectionne) {
      this.replaceNoteInTemplate(this.noteSelectionne);
      
    } else {
      console.error("Le template ou la note sélectionné n'est pas disponible.");
    }
}*/

// Fonction pour sélectionner ou désélectionner toutes les notes
toggleSelectAllNotes() {
  this.allNotesSelected = !this.allNotesSelected;
  this.selectedNotes = this.allNotesSelected ? this.notes.map(note => `${note.module}-${note.valeur}`) : [];
  console.log('Notes sélectionnées:', this.selectedNotes);
  
  // Vérifiez si toutes les notes sont maintenant sélectionnées
  this.checkAllNotesSelected();

  // Appel de la fonction pour remplacer dans le template
  this.replaceNotesInTemplate(this.selectedNotes.join(', '));
}

// Gérer la sélection multiple
onNotesChange(event: any) {
  this.notesSelectionnees = event.value.map(note => `${note.module}-${note.valeur}`);
  console.log('Notes sélectionnées:', this.notesSelectionnees);

  // Vérifiez si toutes les notes sont maintenant sélectionnées
  this.checkAllNotesSelected();

  // Appel de la fonction pour remplacer dans le template
  if (this.notesSelectionnees.length > 0) {
      this.replaceNotesInTemplate(this.notesSelectionnees.join(', '));
  } else {
      console.error("Aucune note sélectionnée.");
  }
}

// Vérifiez si toutes les notes sont sélectionnées
checkAllNotesSelected() {
  this.allNotesSelected = this.notes.length > 0 && this.notes.every(note => this.selectedNotes.includes(`${note.module}-${note.valeur}`));
}

// Fonction pour vérifier si une note est sélectionnée
isNoteSelected(note: any): boolean {
  return this.selectedNotes.includes(`${note.module}-${note.valeur}`);
}


// Fonction pour remplacer les notes dans le template
replaceNotesInTemplate(notes: string): void {
  if (this.templatePromptForSelectNote) {
    const finalPrompt = this.templatePromptForSelectNote.replace(/{{noteSelectionne}}/g, notes);
    console.log('Template après remplacement:', finalPrompt);

    // Envoyer le prompt à l'IA ou l'utiliser ailleurs
    this.submitNoteTemplate(finalPrompt);
  } else {
    console.error('Le templatePrompt est vide ou non récupéré.');
  }
}



// Remplacer la variable {{noteSelectionne}} dans le templatePrompt
/*replaceNoteInTemplate(niveau: string): void {
  if (this.templatePromptForSelectNote) {
    // Utilisation de replace pour remplacer {{niveauSelectionne}} par le niveau sélectionné
    const finalPrompt = this.templatePromptForSelectNote.replace(/{{noteSelectionne}}/g, niveau);
    console.log('Template après remplacement:', finalPrompt);

    // Vous pouvez maintenant envoyer ce prompt à l'IA ou l'utiliser ailleurs
    // Appeler la fonction pour soumettre le template après remplacement
    this.submitNoteTemplate(finalPrompt);
  } else {
    console.error('Le templatePrompt est vide ou non récupéré.');
  }
}*/

submitNoteTemplate(finalPrompt: string): void {
  this.loading = true;

  // Envoi de la requête HTTP avec le template mis à jour
  //  this.http.post('http://51.77.245.237:7180/career/generateCareerRecommendationsByTemplate', finalPrompt, {
  this.http.post('http://localhost:8181/career/generateCareerRecommendationsByTemplate', finalPrompt, {
    headers: { 'Content-Type': 'text/plain' }
  })
  .subscribe(
    (response: CareerRecommendation[]) => {
      // Traiter la réponse comme un tableau de recommandations de carrière
      this.careerOptions = response;
      this.loading = false;  // Arrêter le chargement
    },
    (error: any) => {
      this.handleError(error);  // Gérer les erreurs
      this.loading = false;  // Arrêter le chargement
    }
  );
}



  // Ajoute ou retire un élément de la sélection
  toggleSelection(item: Cinematique) {
    const index = this.selectedItems.findIndex(i => i.idCinematique === item.idCinematique);
    if (index > -1) {
      this.selectedItems.splice(index, 1);
    } else if (this.selectedItems.length < this.maxSelection) {
      this.selectedItems.push(item);
    } 
  }

  // Fonction appelée lorsque l'utilisateur sélectionne un paramètre dans la liste
  onParametreChange(event: any) {
    this.selectedParametre = event.target.value; // Stocke le code du paramètre sélectionné
    console.log('Paramètre sélectionné:', this.selectedParametre); // Affiche le paramètre sélectionné dans la console
  }

  // Vérifie si un élément est sélectionné
  isSelected(item: Cinematique): boolean {
    return this.selectedItems.some(i => i.idCinematique === item.idCinematique);
  }

  submitSelections() { 
    const selectedChoices = this.selectedItems.map(item => item.intitule);
    this.loading = true;

    if (this.templatePromptDescription) {
      const requestBody = {
        preferences: selectedChoices,
        templatePrompt: this.templatePromptDescription
      };
      console.log('Selected Choices:', selectedChoices);
      console.log('Template Prompt Description:', this.templatePromptDescription);
      console.log('Template envoyé:', requestBody);

      // Envoyer la requête HTTP avec le template et les préférences
      this.http.post<CareerRecommendation[]>('http://localhost:8181/career/generateCareerRecommendations', requestBody)
        .subscribe(
          (response: CareerRecommendation[]) => {
            // Traiter la réponse comme un tableau de recommandations de carrière
            this.careerOptions = response;
            this.loading = false;  // Arrêter le chargement
          },
          (error: any) => {
            this.handleError(error);  // Gérer les erreurs
            this.loading = false;  // Arrêter le chargement
          }
        );
    } else {
      console.error('Le templatePrompt est vide ou non récupéré.');
      this.loading = false;
    } 
  }

  // Gestion de la réponse
  handleResponse(response: any) {
    this.careerOptions = this.careerOptions.concat(response.careerOptions || []);
    this.message = 'Les recommandations ont été mises à jour !';
    this.loading = false;
  }

  // Gestion des erreurs
  handleError(error: any) {
    this.loading = false;
    console.error('Erreur lors de la requête:', error);
    this.message = 'Une erreur est survenue lors de la génération des recommandations.';
  }

  // Renvoie les éléments à afficher en fonction de l'onglet courant
  getCurrentItems(): Cinematique[] {
    return this.cinematiques;
  }

  // Calcule la largeur de la barre de progression en fonction des éléments sélectionnés
  get progressWidth() {
    return (this.selectedItems.length / this.maxSelection) * 100 + '%';
  }  
}

import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CareerRecommendation } from 'app/class/CareerRecommendation';

@Component({
  selector: 'app-parametre',
  templateUrl: './parametre.component.html',
  styleUrls: ['./parametre.component.scss']
})
export class ParametreComponent implements OnInit {
  stepIndex = 0;    // Indicateur de l'étape actuelle dans l'interface utilisateur
  careerRecommendations: CareerRecommendation[] = [];        // Stocke les recommandations de carrière renvoyées par le backend
  loading = false;    // Indicateur de chargement pendant l'appel à l'API
  message: string = '';  // Message pour afficher l'état de la requête ou les erreurs
    careerOptions: any[] = [];



  steps = [
    {
      text: 'Rester assis | Bouger',
      choices: [
        { label: 'Faire des inventaires et tenir des registres', selected: false },
        { label: 'Travailler sur des voitures', selected: false },
        { label: 'Poser, réparer de la plomberie', selected: false },
        { label: 'Utiliser une machine sur une chaîne de production', selected: false },
        { label: 'Enseigner à une classe de lycée', selected: false },
        { label: 'Maîtriser une documentation technique', selected: false },
        { label: 'Prendre soin des animaux', selected: false },
        { label: 'Porter ou manipuler des objets, des charges', selected: false },
        { label: 'Assurer une gestion documentaire', selected: false },
        { label: 'Être dans la nature', selected: false },
        { label: 'Poser des murs ou des toits', selected: false },
        { label: 'Travailler de mes mains', selected: false },
        { label: 'S\'occuper des enfants ', selected: false },
        { label: 'Écrire des livres', selected: false },
        { label: 'Cultiver, récolter', selected: false },
        { label: 'Apprendre à coder', selected: false },
        { label: 'Dessiner', selected: false },
        { label: 'Communication digitale', selected: false },
        { label: 'utiliser internet', selected: false },
        { label: 'Voyage', selected: false },
        { label: 'Culture', selected: false }






      ]
    }
  ];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {}

  get currentStepText() {
    return this.steps[this.stepIndex].text;
  }

  get currentChoices() {
    return this.steps[this.stepIndex].choices;
  }

  prevStep() {
    if (this.stepIndex > 0) {
      this.stepIndex--;
    }
  }

  nextStep() {
    if (this.stepIndex < this.steps.length - 1) {
      this.stepIndex++;
    }
  }

    // Récupère les choix sélectionnés par l'utilisateur

  getSelectedChoices() {
    return this.steps.reduce((acc, step) => {
      const selectedChoices = step.choices
        .filter(choice => choice.selected)
        .map(choice => choice.label);
      return acc.concat(selectedChoices);
    }, []);
  }


         
              

              submitSelections() {
                const selectedChoices = this.getSelectedChoices();
                this.loading = true;
               // Afficher les choix sélectionnés dans la console
                console.log('Choix sélectionnés:', selectedChoices);
                this.http.post('http://localhost:8080/generateCareerRecommendations', selectedChoices)
                  .subscribe(
                    (response: any) => {
                      this.handleResponse(response);
                    },
                    error => {
                      this.handleError(error);
                    }
                  );
              }


              handleResponse(response: any) {
                this.careerOptions = response.careerOptions || [];
                this.message = 'Les recommandations de carrière ont été générées avec succès!';
                this.loading = false;
              }
            
              handleError(error: any) {
                this.loading = false;
                console.error('Erreur lors de la requête à l\'IA:', error);
                this.message = 'Une erreur est survenue lors de la génération des recommandations. Veuillez réessayer plus tard.';
              }



      
}

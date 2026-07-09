import { Component, OnInit } from '@angular/core';
import { MTResponse } from 'app/class/MTResponse';
import { Parametre } from 'app/class/Parametre';
import { ParametreService } from 'app/services/parametre.service';

@Component({
  selector: 'app-objectif-secondaire',
  templateUrl: './objectif-secondaire.component.html',
  styleUrls: ['./objectif-secondaire.component.css']
})
export class ObjectifSecondaireComponent implements OnInit {
  // Liste d'objectifs initiale
  objectifs: string[] = ['Améliorer mes notes', 
    'Gérer mon temps',
    'Préparer mes examens',
    'Bénéficier de bourses scolaires',
    'Avoir un contrat dans une entreprise',
    'Etre le major de promotion',
    'Avoir la moyenne',
    'Avoir la meilleure mention',
    'Etre exellent dans mon domaine de recherche'
    ];
  niveaux: any[] = [];
  parametres: Parametre[] = [];
  niveauSelectionne: string | null = null;

  notes: any[] = [];
  noteSelectionne: string | null = null;




  constructor(    private parametreService: ParametreService  ) { }

  ngOnInit(): void {
    
    // Récupération des paramètres -niveau
    this.parametreService.findAll().subscribe(response => {
      if (response && response.payload) {
        this.parametres = response.payload;
      } else {
        console.error('Erreur: Données non disponibles');
      }
    }, error => {
      console.error('Erreur de récupération des paramètres', error);
    });

    

  }


  // Gestion de la sélection du niveau d'étude
  onEducationLevelChange(event: any): void {
    this.niveauSelectionne = event.target.value;
    console.log('Niveau sélectionné:', this.niveauSelectionne);

  }
  

  // Fonction pour ajouter un objectif (tu peux personnaliser cette partie pour ajouter une entrée utilisateur)
  ajouterObjectif() {
    const nouvelObjectif = prompt("Entrez un nouvel objectif :");
    if (nouvelObjectif) {
      this.objectifs.push(nouvelObjectif);
    }
  }

  // Fonction pour envoyer les objectifs sélectionnés
  envoyer() {
    alert("Objectifs envoyés : " + this.objectifs.join(', '));
  }

}

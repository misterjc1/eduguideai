import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MTResponse } from 'app/class/MTResponse';
import { Parametre } from 'app/class/Parametre';
import { ParametreService } from 'app/services/parametre.service';

@Component({
  selector: 'app-simulation-effort',
  templateUrl: './simulation-effort.component.html',
  styleUrls: ['./simulation-effort.component.css']
})
export class SimulationEffortComponent implements OnInit {
  niveaux: any[] = [];  // Liste des niveaux disponibles
  niveauSelectionne: any | null = null;  // Niveau sélectionné
  notes: any[] = [];  // Liste des notes disponibles
  notesSelectionnees: any[] = [];  // Notes sélectionnées

  constructor(private router: Router, private parametreService: ParametreService) {}

  ngOnInit(): void {
    // Récupérer les niveaux d'étude et les notes depuis le service
    this.parametreService.findAll().subscribe((response: MTResponse<any[]>) => {
      if (response.status === 'OK' && response.payload) {
        this.niveaux = response.payload.filter(niveau => !niveau.deleted);
      } else {
        console.error('Erreur dans la réponse de l\'API:', response.message);
      }
    });

    this.parametreService.findAllNote().subscribe((response: MTResponse<any[]>) => {
      if (response.status === 'OK' && response.payload) {
        this.notes = response.payload.filter(note => !note.deleted);
      } else {
        console.error('Erreur dans la réponse de l\'API:', response.message);
      }
    });
  }

  // Gestion de la sélection du niveau d'étude (choix unique)
  onEducationLevelChange(event: any): void {
    this.niveauSelectionne = event.target.value;
    this.updateApercu();
  }

  // Gestion de la sélection des notes (choix multiple)
  onNotesChange(event: any): void {
    const selectedOptions = event.target.selectedOptions;
    this.notesSelectionnees = Array.from(selectedOptions).map((option: any) => option.value);
    this.updateApercu();
  }

  // Mise à jour de l'aperçu des paramètres sélectionnés
  updateApercu(): void {
    console.log('Niveau sélectionné:', this.niveauSelectionne);
    console.log('Notes sélectionnées:', this.notesSelectionnees);
  }

  // Méthode pour envoyer les paramètres sélectionnés
  envoyer(): void {
    if (this.niveauSelectionne && this.notesSelectionnees.length > 0) {
      const parametres = {
        niveau: this.niveauSelectionne,
        notes: this.notesSelectionnees
      };

      console.log('Paramètres envoyés:', parametres);
      this.router.navigate(['/niveau-suivant'], { state: { data: parametres } });
    } else {
      alert('Veuillez sélectionner un niveau d\'étude et au moins une note.');
    }
  }
}


import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-analyse-third',
  templateUrl: './analyse-third.component.html',
  styleUrls: ['./analyse-third.component.css']
})
export class AnalyseThirdComponent implements OnInit {
  @Input() niveauSelectionne: any; // Niveau sélectionné, passé par le parent
  @Input() notesSelectionnees: any[] = []; // Notes sélectionnées
  @Input() objectifSelectionne: string = ''; // Objectif sélectionné

  resultatsSimulation: any[] = []; // Tableau pour stocker les résultats de la simulation
  moyenne: number = 0; // Moyenne des notes

  ngOnInit(): void {
    if (this.notesSelectionnees && this.notesSelectionnees.length > 0) {
      this.analyserResultats(this.notesSelectionnees);
    }
  }

  // Méthode pour analyser les résultats
  analyserResultats(notes: any[]): void {
    let totalPoints = 0;
    let count = 0;

    const objectifs: { [key: string]: number } = {
      'Valider avec une moyenne de 10/20': 10,
      'Valider avec une moyenne de 12/20': 12,
      'Excellence académique': 15,
    };

    const objectifCible = objectifs[this.objectifSelectionne] || 10; // Valeur par défaut si l'objectif est non défini

    this.resultatsSimulation = notes.map(note => {
      totalPoints += note.valeur;
      count++;

      let message = '';

      if (note.valeur < objectifCible) {
        const pointsManquants = objectifCible - note.valeur;
        message = `Il vous manque ${pointsManquants.toFixed(2)} point(s) pour atteindre ${objectifCible} dans ${note.module}.`;
      } else {
        message = `Vous avez atteint votre objectif avec ${note.valeur} dans ${note.module}!`;
      }

      return {
        module: note.module,
        valeur: note.valeur,
        message,
      };
    });

    this.moyenne = totalPoints / count;
  }
}

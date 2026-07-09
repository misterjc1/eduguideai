// export interface SimulationEffort {
//     matiere: string;
//     noteActuelle: string;
//     effortFournir: string;
//     pointFort:string;
//     domaineAmeliore:string;
//     conseilPersonalise:string;
//   }
  


import { ChartTypeRegistry } from 'chart.js';

export interface SimulationEffort {
  matiere: string;
  noteActuelle: string;
  effortFournir: string;
  pointFort: string;
  domaineAmeliore: string;
  conseilPersonalise: string;
  graphique?: {
    type: keyof ChartTypeRegistry; // Utilisation des types de graphique définis par Chart.js
    data: {
      labels: string[];
      datasets: Array<{
        label: string;
        data: number[];
        backgroundColor: string[];
        borderColor: string[];
        borderWidth: number;
      }>;
    };
    options: {
      scales: {
        y: Array<{
          ticks: {
            beginAtZero: boolean;
          };
        }>;
      };
    };
  };
}

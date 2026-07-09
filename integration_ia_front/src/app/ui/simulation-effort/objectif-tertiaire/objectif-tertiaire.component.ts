import { Component, Input, ViewChildren, ElementRef, AfterViewInit, QueryList } from '@angular/core';
import { SimulationEffort } from 'app/class/SimulationEffort'; 
import { Chart } from 'chart.js';

@Component({
  selector: 'app-objectif-tertiaire',
  templateUrl: './objectif-tertiaire.component.html',
  styleUrls: ['./objectif-tertiaire.component.css']
})
export class ObjectifTertiaireComponent implements AfterViewInit {
  @Input() simulationResults: SimulationEffort[] = []; 
  @ViewChildren('chartCanvas') chartCanvases!: QueryList<ElementRef<HTMLCanvasElement>>; 

  ngAfterViewInit() {
    if (this.simulationResults.length > 0) {
      this.generateGraph();
    }
  }

  generateGraph() {
    if (this.chartCanvases && this.chartCanvases.length > 0) {
      this.simulationResults.forEach((result, index) => {
        const ctx = this.chartCanvases.toArray()[index].nativeElement.getContext('2d');
        const graphData = result.graphique;

        if (ctx) {
          new Chart(ctx, {
            type: graphData.type,
            data: graphData.data,
            options: {
              scales: {
                y: { 
                  beginAtZero: true
                }
              }
            }
          });
        } else {
          console.error("Impossible d'obtenir le contexte du canvas pour l'index", index);
        }
      });
    }
  }
}

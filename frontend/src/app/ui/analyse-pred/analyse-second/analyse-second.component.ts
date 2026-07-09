import { Component, Input, OnInit } from '@angular/core';
import * as Chartist from 'chartist';


@Component({
  selector: 'app-analyse-second',
  templateUrl: './analyse-second.component.html',
  styleUrls: ['./analyse-second.component.css']
})
export class AnalyseSecondComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  @Input() data: any; // Déclare les inputs attendus
  @Input() predictedData: any;

  dataLabels: string[] = []; // Initialisation des labels des données

  ngAfterViewInit() {
    // Assure-toi de remplir les dataLabels avec les valeurs appropriées avant d'utiliser Chartist
    this.dataLabels = ['Semestre 1', 'Semestre 2', 'Semestre 3']; // Exemples de labels

    new Chartist.Line('.ct-chart', {
      labels: this.dataLabels,
      series: [this.data, this.predictedData]
    });
  }
  

}

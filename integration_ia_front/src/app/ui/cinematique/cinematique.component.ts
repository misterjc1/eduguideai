import { Component, OnInit } from '@angular/core';
import { DataTableMetaData } from 'app/layouts/datatable.model';
import { CinematiqueService } from 'app/services/cinematique.service';

@Component({
  selector: 'app-cinematique',
  templateUrl: './cinematique.component.html',
  styleUrls: ['./cinematique.component.css']
})
export class CinematiqueComponent implements OnInit {

  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any = [{}];
  d: any;
  
  constructor(private cinematiqueService: CinematiqueService) { }

  ngOnInit(): void {

    this.cinematiqueService.findAll().subscribe(data => {
      this.tabData = data;
      this.tabData = this.tabData.payload;
      console.log(this.tabData);


      this.tabData.forEach(element => {
        
        if (element.typeCinematique !== null) {
          element.typeCinematiqueN = element.typeCinematique.niveau;
        } else {
          element.typeCinematiqueN = "";
        }

        // Gestion de typeService
        if (element.typeService !== null) {
          element.typeServiceN = element.typeService.typeService; // Ou toute autre propriété de typeService
        } else {
          element.typeServiceN = "";
        }

      });
      
      this.datatable.colonnes = [
        { label: 'select', name: 'select', show: true },
        { label: 'Intitulé', name: 'intitule', show: true },
        { label: 'TypeCinematique(niveau,typeBouton,choix)', name: 'typeCinematiqueN', show: true },
        { label: 'Service', name: 'typeServiceN', show: true },
        //{ label: 'TypeRequete', name: 'type', show: true },
        //{ label: 'Image', name: 'image', show: true },

  
      ];
      this.datatable.data = this.tabData; 
      this.datatable.headActions = ['search','add','delete']
      this.datatable.rowActions = ['edit']
      this.datatable.title = 'Liste des cinematique';
      this.datatable.type = 'CINEMATIQUE';
      this.isDataLoaded = true;
    });
  }
}


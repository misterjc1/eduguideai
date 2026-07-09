import { Component, OnInit } from '@angular/core';
import { DataTableMetaData } from 'app/layouts/datatable.model';
import { TypeCinematiqueService } from 'app/services/type-cinematique.service';

@Component({
  selector: 'app-type-cinematique',
  templateUrl: './type-cinematique.component.html',
  styleUrls: ['./type-cinematique.component.css']
})
export class TypeCinematiqueComponent implements OnInit {

  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any = [{}];
  d: any;
  
  constructor(private typeCinematiqueService: TypeCinematiqueService) { }

  ngOnInit(): void {

    this.typeCinematiqueService.findAll().subscribe(data => {
      this.tabData = data;
      this.tabData = this.tabData.payload;
      
      this.datatable.colonnes = [
        { label: 'select', name: 'select', show: true },
        { label: 'Niveau', name: 'niveau', show: true },
        { label: 'Type de Boutton', name: 'typeBouton', show: true },
        { label: 'Choix ', name: 'choixMultiple', show: true },
        //{ label: 'TypeRequete', name: 'type', show: true },
        //{ label: 'Image', name: 'image', show: true },

  
      ];
      this.datatable.data = this.tabData; 
      this.datatable.headActions = ['search','add','delete']
      this.datatable.rowActions = ['edit']
      this.datatable.title = 'Liste des types de cinematique';
      this.datatable.type = 'TYPECINEMATIQUE';
      this.isDataLoaded = true;
    });
  }
}

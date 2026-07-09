import { Component, OnInit } from '@angular/core';
import { DataTableMetaData } from 'app/layouts/datatable.model';
import { NiveauService } from 'app/services/niveau.service';

@Component({
  selector: 'app-niveau',
  templateUrl: './niveau.component.html',
  styleUrls: ['./niveau.component.css']
})
export class NiveauComponent implements OnInit {

  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any = [{}];
  d: any;

  constructor(private niveauService: NiveauService) { }
  ngOnInit(): void {

    this.niveauService.findAll().subscribe(data => {
      this.tabData = data;
      this.tabData = this.tabData.payload;
      
      this.datatable.colonnes = [
        { label: 'select', name: 'select', show: true },
        { label: 'Filiere', name: 'filiere', show: true },
        { label: 'Libellé', name: 'libelle', show: true }
      ];
      this.datatable.data = this.tabData; 
      this.datatable.headActions = ['search','add','delete','upload']
      this.datatable.rowActions = ['edit']
      this.datatable.title = 'Liste des niveaux';
      this.datatable.type = 'NIVEAU';
      this.isDataLoaded = true;
    });
  }

}

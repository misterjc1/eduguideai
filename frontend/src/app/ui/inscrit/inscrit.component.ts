import { Component, OnInit } from '@angular/core';
import { DataTableMetaData } from 'app/layouts/datatable.model';
import { InscritService } from 'app/services/inscrit.service';

@Component({
  selector: 'app-inscrit',
  templateUrl: './inscrit.component.html',
  styleUrls: ['./inscrit.component.css']
})
export class InscritComponent implements OnInit {

  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any = [{}];
  d: any;
  
  constructor(private inscritService: InscritService) { }

  ngOnInit(): void {

    this.inscritService.findAll().subscribe(data => {
      this.tabData = data;
      this.tabData = this.tabData.payload;
      console.log(this.tabData);

      this.tabData.forEach(element => {
    
        if (element.niveau !== null) {
          element.niveauL = element.niveau.filiere + " " + element.niveau.libelle;
        } else {
          element.niveauL = "";
        }

      });
      
      this.datatable.colonnes = [
        { label: 'select', name: 'select', show: true },
        { label: 'Matricule', name: 'matricule', show: true },
        { label: 'Nom', name: 'nom', show: true },
        { label: 'Prénom', name: 'prenom', show: true },
        { label: 'email', name: 'email', show: true },
        { label: 'Telephone', name: 'telephone', show: true },
        { label: 'Niveau', name: 'niveauL', show: true },

      ];
      this.datatable.data = this.tabData; 
      this.datatable.headActions = ['search','add','delete','upload']
      this.datatable.rowActions = ['edit']
      this.datatable.title = 'Liste des inscrits';
      this.datatable.type = 'INSCRIT';
      this.isDataLoaded = true;
    });
  }

}

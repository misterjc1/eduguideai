import { Component, OnInit } from '@angular/core';
import { DataTableMetaData } from 'app/layouts/datatable.model';
import { UtilisateurClassService } from 'app/services/utilisateur-class.service';

@Component({
  selector: 'app-utilisateur-class',
  templateUrl: './utilisateur-class.component.html',
  styleUrls: ['./utilisateur-class.component.css']
})
export class UtilisateurClassComponent implements OnInit {

  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any = [{}];
  d: any;

  constructor(private utilisateurClassService: UtilisateurClassService,) { }

  ngOnInit(): void {

    this.utilisateurClassService.findAll().subscribe(data => {
      this.tabData = data;
      this.tabData = this.tabData.payload;
      
      this.datatable.colonnes = [
        { label: 'select', name: 'select', show: true },
        { label: 'Username', name: 'username', show: true },
        { label: 'Nom', name: 'nom', show: true },
        { label: 'Prénom', name: 'prenom', show: true },
        { label: 'email', name: 'email', show: true },
        { label: 'Telephone', name: 'telephone', show: true },
        { label: 'Type', name: 'type', show: true },


      ];
      this.datatable.data = this.tabData; 
      this.datatable.headActions = ['search','add','delete']
      this.datatable.rowActions = ['edit', 'delete']
      this.datatable.title = 'Liste des utilisateurs';
      this.datatable.type = 'UTILISATEURCLASS';
      this.isDataLoaded = true;
    });
  }

  

}

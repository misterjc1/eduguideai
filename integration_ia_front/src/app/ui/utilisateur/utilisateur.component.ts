import { Component, OnInit } from '@angular/core';
import { UtilisateurClass } from 'app/class/UtilisateurClass';
import { DataTableMetaData } from 'app/layouts/datatable.model';
import { UtilisateursService } from 'app/services/utilisateurs.service';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import { UtilisateurService } from 'app/user-module/service/utilisateur.service';

@Component({
  selector: 'app-utilisateur',
  templateUrl: './utilisateur.component.html',
  styleUrls: ['./utilisateur.component.css']
})
export class UtilisateurComponent implements OnInit {

  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any = [{}];
  d: any = [{}];
  adminRole: boolean = false;
  utilisateurs: UtilisateurClass;
  user: Utilisateur;
  userConnected: any = {};

  constructor(private utilisateursService: UtilisateursService,
    private tokenService: TokenStorageService,
    private utilisateurService: UtilisateurService, ) { }

  ngOnInit() {
    this.utilisateursService.findAll().subscribe(data => {
      this.tabData = data;
      this.tabData = this.tabData.payload;

      this.tabData.forEach(element => {
        element.nomComplet =  element.nom +' '+ element.prenom
      });
      this.datatable.colonnes = [
        { label: 'select', name: 'select', show: true },
        { label: 'Nom complet', name: 'nomComplet', show: true },
        { label: 'Téléphone ', name: 'telephone1', show: true },
        { label: 'Email', name: 'email', show: true },
        { label: 'Date de Naissance ', name: 'dateNaissance', show: true },
        //{ label: 'Zone de couverture', name: 'zoneCouverture', show: true },
        //{ label: 'Fonction', name: 'fonction', show: true },
        { label: 'Type', name: 'type', show: true },

      ];
      this.datatable.title = 'Liste des personnes';
      this.datatable.headActions = ['search','add','delete','upload']
      this.datatable.rowActions = ['edit','objectifs','pin']
      this.datatable.data = this.tabData;
      this.datatable.type = 'UTILISATEUR';
      this.isDataLoaded = true;
    });
  }
}





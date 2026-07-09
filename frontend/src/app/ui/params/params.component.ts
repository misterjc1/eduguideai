import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CareerRecommendation } from 'app/class/CareerRecommendation';
import { Parametre } from 'app/class/Parametre';
import { DataTableMetaData } from 'app/layouts/datatable.model';
import { ParametreService } from 'app/services/parametre.service';
import { UtilisateurService } from 'app/services/utilisateur.service';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';

@Component({
  selector: 'app-params',
  templateUrl: './params.component.html',
  styleUrls: ['./params.component.css']
})
export class ParamsComponent implements OnInit {
  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any = [{}];
  d: any = [{}];
  parametre: Parametre;
  adminRole: boolean = false;
  user: Utilisateur;
  userConnected: any = {};

  constructor(private ParametreService: ParametreService, public dialog: MatDialog,
    private TokenService: TokenStorageService,
    private utilisateurService: UtilisateurService,
    private parametreService: ParametreService) {
    this.parametre = new Parametre();
    this.user = new Utilisateur();
  }

  ngOnInit(): void {
    this.parametreService.findAll().subscribe(data => {
      this.tabData = data;
      this.tabData = this.tabData.payload;
      this.datatable.colonnes = [
        { label: 'select', name: 'select', show: true },
        { label: 'Libellé', name: 'parametres', show: true },
        //{ label: 'Adresse', name: 'adresse', show: true },
        //{ label: 'Téléphone', name: 'telephone', show: true },
        //{ label: 'Responsable', name: 'responsable', show: true },
      ];
      this.datatable.headActions = ['search', 'add', 'delete']
      this.datatable.rowActions = ['edit']
      this.datatable.title = 'Liste des parametres';
      this.datatable.data = this.tabData;
      this.datatable.type = 'PARAMETRE';
      this.isDataLoaded = true;
    });
  }
}

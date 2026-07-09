import { Utilisateur } from './../../class/Utilisateur';
import { TokenStorageService } from './../../service/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { DataTableMetaData } from 'app/user-module/layouts/datatable.model';
import { UtilisateurService } from 'app/user-module/service/utilisateur.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-utilisateur',
  templateUrl: './utilisateur.component.html',
  styleUrls: ['./utilisateur.component.scss']
})
export class UtilisateurComponent implements OnInit {

  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any[] = [];
  errorMessage: string = '';

  user: Utilisateur;
  userConnected: any = {};

  constructor(
    private utilisateurService: UtilisateurService,
    private tokenService: TokenStorageService,
  ) {}

  ngOnInit(): void {
    this.userConnected = this.tokenService.getUser();
    this.utilisateurService.findAll().pipe(
      catchError(() => of({ payload: [] }))
    ).subscribe((data: any) => {
      this.tabData = data?.payload || [];
      this.datatable.colonnes = [
        { label: 'select', name: 'select', show: true },
        { label: 'Nom d\'utilisateur', name: 'username', show: true },
        { label: 'Type', name: 'type', show: true },
        { label: 'Email', name: 'email', show: true },
      ];
      this.datatable.headActions = ['search', 'add', 'delete'];
      this.datatable.rowActions = ['edit'];
      this.datatable.title = 'Liste des utilisateurs';
      this.datatable.data = this.tabData;
      this.datatable.type = 'UTILISATEUR';
      this.isDataLoaded = true;
    });
  }
}

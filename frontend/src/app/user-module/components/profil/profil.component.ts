import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { Component, OnInit } from '@angular/core';
import { DataTableMetaData } from 'app/user-module/layouts/datatable.model';
import { ProfilService } from 'app/user-module/service/profil.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit {

  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any = [{}];
  d: any = [];
  user: Utilisateur;
  userConnected: any = {};

  adminRole: boolean = false;

  constructor(private profilService: ProfilService,
    private tokenService: TokenStorageService,
    ) { }

  ngOnInit(): void {
    this.userConnected = this.tokenService.getUser();
    this.adminRole = this.userConnected?.type === 'AGENT';
    this.profilService.findAll().pipe(
      catchError(() => of({ payload: [] } as any))
    ).subscribe((data: any) => {
      this.tabData = data?.payload || [];
      this.datatable.colonnes = [
        { label: 'select', name: 'select', show: true },
        { label: 'Intitulé', name: 'intitule', show: true },
        { label: 'Description', name: 'description', show: true },
      ];
      this.datatable.headActions = ['search', 'add', 'delete'];
      this.datatable.rowActions = ['edit'];
      this.datatable.title = 'Liste des profils';
      if (this.adminRole) {
        this.datatable.data = this.tabData;
      } else {
        this.datatable.data = this.tabData.filter((e: any) => e.intitule !== 'Administrateurs');
      }
      this.datatable.type = 'PROFIL';
      this.isDataLoaded = true;
    });
    
  }

}

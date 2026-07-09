import { TokenStorageService } from './../../service/token-storage.service';
import { UtilisateurService } from 'app/user-module/service/utilisateur.service';
import { Utilisateur } from './../../class/Utilisateur';
import { Component, OnInit } from '@angular/core';
import { DataTableMetaData } from 'app/user-module/layouts/datatable.model';
import { SessionService } from 'app/user-module/service/session.service';
import { TerminalService } from 'app/user-module/service/terminal.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import * as moment from 'moment';

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.scss']
})
export class SessionComponent implements OnInit {
  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any = [{}];
  d: any = [{}];

  adminRole: boolean = false;
  user: Utilisateur;
  userConnected: any = {};

  constructor(private sessionService: SessionService,
    private terminalService: TerminalService,
    private utilisateurService: UtilisateurService,
    private tokenService: TokenStorageService,) { }


  ngOnInit(): void {
    this.userConnected = this.tokenService.getUser();
    this.adminRole = this.userConnected?.type === 'AGENT';
    const sessionCols = [
      { label: 'Date et heure de début', name: 'dateDebut', show: true },
      { label: 'Date et heure de fin', name: 'dateFin', show: true },
      { label: 'Statut', name: 'statut', show: true },
      { label: 'Utilisateur', name: 'utilisateurUsername', show: true },
    ];

    const formatSessions = (sessions: any[]) => {
      sessions.forEach(element => {
        if (element.startDate) element.dateDebut = moment(element.startDate).utc().format('DD/MM/YYYY à HH:mm:ss');
        if (element.endDate) element.dateFin = moment(element.endDate).utc().format('DD/MM/YYYY à HH:mm:ss');
        element.utilisateurUsername = element.utilisateur?.username || '-';
      });
    };

    if (this.adminRole) {
      this.sessionService.findAll().pipe(
        catchError(() => of({ payload: [] } as any))
      ).subscribe((data: any) => {
        this.tabData = data?.payload || [];
        formatSessions(this.tabData);
        this.datatable.colonnes = sessionCols;
        this.datatable.title = 'Historique de connexions';
        this.datatable.data = this.tabData;
        this.isDataLoaded = true;
        this.datatable.type = 'SESSION';
      });
    } else {
      this.utilisateurService.findByCode(this.userConnected?.codeUtilisateur).pipe(
        catchError(() => of({ payload: [] } as any))
      ).subscribe((result: any) => {
        this.user = result?.payload?.[0];
        if (!this.user?.codeUtilisateur) {
          this.isDataLoaded = true;
          return;
        }
        this.sessionService.findByUser(this.user.codeUtilisateur).pipe(
          catchError(() => of({ payload: [] } as any))
        ).subscribe((res: any) => {
          this.tabData = res?.payload || [];
          formatSessions(this.tabData);
          this.datatable.colonnes = sessionCols;
          this.datatable.title = 'Historique de connexions';
          this.datatable.data = this.tabData;
          this.isDataLoaded = true;
          this.datatable.type = 'SESSION';
        });
      });
    }
    
  }

}

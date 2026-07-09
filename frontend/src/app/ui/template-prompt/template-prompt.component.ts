import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TemplatePrompt } from 'app/class/TemplatePrompt';
import { DataTableMetaData } from 'app/layouts/datatable.model';
import { TemplatePromptService } from 'app/services/template-prompt.service';
import { UtilisateurService } from 'app/services/utilisateur.service';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';

@Component({
  selector: 'app-template-prompt',
  templateUrl: './template-prompt.component.html',
  styleUrls: ['./template-prompt.component.css']
})
export class TemplatePromptComponent implements OnInit {
  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any = [{}];
  d: any = [{}];
  templatePrompt: TemplatePrompt;
  adminRole: boolean = false;
  user: Utilisateur;
  userConnected: any = {};

  constructor(private TemplatePromptService: TemplatePromptService, public dialog: MatDialog,
    private TokenService: TokenStorageService,
    private utilisateurService: UtilisateurService,
    private templatePromptService: TemplatePromptService) {
    this.templatePrompt = new TemplatePrompt();
    this.user = new Utilisateur();
  }

  ngOnInit(): void {
    this.templatePromptService.findAll().subscribe(data => {
      this.tabData = data;
      this.tabData = this.tabData.payload;
      this.datatable.colonnes = [
        { label: 'select', name: 'select', show: true },
        { label: 'Code', name: 'codeTemplatePrompt', show: true },
        { label: 'Variables', name: 'variables', show: true },
        { label: 'Description', name: 'description', show: true },
        //{ label: 'Téléphone', name: 'telephone', show: true },
        //{ label: 'Responsable', name: 'responsable', show: true },
      ];
      this.datatable.headActions = ['search', 'add', 'delete']
      this.datatable.rowActions = ['edit']
      this.datatable.title = 'Liste des template de prompts';
      this.datatable.data = this.tabData;
      this.datatable.type = 'TEMPLATEPROMPT';
      this.isDataLoaded = true;
    });
  }
}

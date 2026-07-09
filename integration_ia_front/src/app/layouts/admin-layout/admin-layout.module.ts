import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule, MatRippleModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatRadioModule } from '@angular/material/radio';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTabsModule } from '@angular/material/tabs';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatBadgeModule } from '@angular/material/badge';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatStepperModule } from '@angular/material/stepper';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../dashboard/dashboard.component';
import { UserModuleModule } from 'app/user-module/user-module.module';
import { MatCardModule } from '@angular/material/card';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UtilisateurComponent } from 'app/ui/utilisateur/utilisateur.component';
import { PromptComponent } from 'app/ui/prompt/prompt.component';
import { NewPromptComponent } from 'app/ui/prompt/new-prompt/new-prompt.component';
import { NewUtilisateurComponent } from 'app/ui/utilisateur/new-utilisateur/new-utilisateur.component';
import { DonneesComponent } from 'app/ui/donnees/donnees.component';
import { NewDonneesComponent } from 'app/ui/donnees/new-donnees/new-donnees.component';
import { CinematiqueComponent } from 'app/ui/cinematique/cinematique.component';
import { NewCinematiqueComponent } from 'app/ui/cinematique/new-cinematique/new-cinematique.component';
import { TemplatePromptComponent } from 'app/ui/template-prompt/template-prompt.component';
import { NewTemplatePromptComponent } from 'app/ui/template-prompt/new-template-prompt/new-template-prompt.component';
import { ReponseComponent } from 'app/ui/reponse/reponse.component';
import { NewReponseComponent } from 'app/ui/reponse/new-reponse/new-reponse.component';
import { ParamsComponent } from 'app/ui/params/params.component';
import { OrientProComponent } from 'app/ui/orient-pro/orient-pro.component';
import { NewParamsComponent } from 'app/ui/params/new-params/new-params.component';
import { TypeCinematiqueComponent } from 'app/ui/type-cinematique/type-cinematique.component';
import { NewTypeCinematiqueComponent } from 'app/ui/type-cinematique/new-type-cinematique/new-type-cinematique.component';
import { ServiceComponent } from 'app/ui/service/service.component';
import { NewServiceComponent } from 'app/ui/service/new-service/new-service.component';
import { SimulationEffortComponent } from 'app/ui/simulation-effort/simulation-effort.component';
import { AnalysePredComponent } from 'app/ui/analyse-pred/analyse-pred.component';
import { AssistantVComponent } from 'app/ui/assistant-v/assistant-v.component';
import { DetectionFraudComponent } from 'app/ui/detection-fraud/detection-fraud.component';
import { MatIconModule } from '@angular/material/icon';
import { DeuxiemeNiveauComponent } from 'app/ui/orient-pro/deuxieme-niveau/deuxieme-niveau.component';
import { TroisiemeNiveauComponent } from 'app/ui/orient-pro/troisieme-niveau/troisieme-niveau.component';
import { UploadDonneesComponent } from 'app/ui/donnees/upload-donnees/upload-donnees.component';
import { NewOrientProComponent } from 'app/ui/orient-pro/new-orient-pro/new-orient-pro.component';
import { NewSimulationEffortComponent } from 'app/ui/simulation-effort/new-simulation-effort/new-simulation-effort.component';
import { NewAnalysePredictiveComponent } from 'app/ui/analyse-pred/new-analyse-predictive/new-analyse-predictive.component';
import { NewAssistantVirtuelComponent } from 'app/ui/assistant-v/new-assistant-virtuel/new-assistant-virtuel.component';
import { NewDetectionFraudComponent } from 'app/ui/detection-fraud/new-detection-fraud/new-detection-fraud.component';
import { ObjectifSecondaireComponent } from 'app/ui/simulation-effort/objectif-secondaire/objectif-secondaire.component';
import { ObjectifTertiaireComponent } from 'app/ui/simulation-effort/objectif-tertiaire/objectif-tertiaire.component';
import { AnalyseFirstComponent } from 'app/ui/analyse-pred/analyse-first/analyse-first.component';
import { AnalyseSecondComponent } from 'app/ui/analyse-pred/analyse-second/analyse-second.component';
import { AnalyseThirdComponent } from 'app/ui/analyse-pred/analyse-third/analyse-third.component';
import { MatListModule } from '@angular/material/list';
import { OrientProSidebarComponent } from 'app/ui/orient-pro/orient-pro-sidebar/orient-pro-sidebar.component';
import { InscritComponent } from 'app/ui/inscrit/inscrit.component';
import { LiaisonComponent } from 'app/ui/liaison/liaison.component';
import { InscritTuteurComponent } from 'app/ui/inscrit/inscrit-tuteur/inscrit-tuteur.component';
import { NewInscritComponent } from 'app/ui/inscrit/new-inscrit/new-inscrit.component';
import { UploadInscritComponent } from 'app/ui/inscrit/upload-inscrit/upload-inscrit.component';
import { LiaisonDetailsComponent } from 'app/ui/liaison/liaison-details/liaison-details.component';
import { NewLiaisonComponent } from 'app/ui/liaison/new-liaison/new-liaison.component';
import { NiveauComponent } from 'app/ui/niveau/niveau.component';
import { NewNiveauComponent } from 'app/ui/niveau/new-niveau/new-niveau.component';
import { UploadNiveauComponent } from 'app/ui/niveau/upload-niveau/upload-niveau.component';
import { UtilisateurClassComponent } from 'app/ui/utilisateur-class/utilisateur-class.component';
import { NewUtilisateurClassComponent } from 'app/ui/utilisateur-class/new-utilisateur-class/new-utilisateur-class.component';
import { MtDataTableComponent } from '../mt-data-table/mt-data-table.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatTreeModule } from '@angular/material/tree';
import { IdleWarningDialogComponent } from './idle-warning-dialog.component';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatRippleModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatListModule,
    MatTooltipModule,
    MatDialogModule,
    MatTableModule,
    MatSortModule,
    MatSnackBarModule,
    MatRadioModule,
    MatPaginatorModule,
    MatTabsModule,
    MatCheckboxModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    MatBadgeModule,
    MatSlideToggleModule,
    MatExpansionModule,
    UserModuleModule,
    MatStepperModule,
    MatCardModule,
    MatTreeModule,
    MatAutocompleteModule,
    MatToolbarModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
  ],
  declarations: [
    DashboardComponent,
    MtDataTableComponent,
    UtilisateurComponent,
    PromptComponent,
    NewPromptComponent,
    NewUtilisateurComponent,
    DonneesComponent,
    NewDonneesComponent,
    CinematiqueComponent,
    NewCinematiqueComponent,
    TemplatePromptComponent,
    NewTemplatePromptComponent,
    ReponseComponent,
    NewReponseComponent,
    ParamsComponent,
    OrientProComponent,
    OrientProSidebarComponent,
    NewParamsComponent,
    TypeCinematiqueComponent,
    NewTypeCinematiqueComponent,
    ServiceComponent,
    NewServiceComponent,
    SimulationEffortComponent,
    AnalysePredComponent,
    AssistantVComponent,
    DetectionFraudComponent,
    DeuxiemeNiveauComponent,
    TroisiemeNiveauComponent,
    UploadDonneesComponent,
    NewOrientProComponent,
    NewSimulationEffortComponent,
    NewAnalysePredictiveComponent,
    NewAssistantVirtuelComponent,
    NewDetectionFraudComponent,
    ObjectifSecondaireComponent,
    ObjectifTertiaireComponent,
    AnalyseFirstComponent,
    AnalyseSecondComponent,
    AnalyseThirdComponent,
    InscritComponent,
    LiaisonComponent,
    InscritTuteurComponent,
    NewInscritComponent,
    UploadInscritComponent,
    LiaisonDetailsComponent,
    NewLiaisonComponent,
    NiveauComponent,
    NewNiveauComponent,
    UploadNiveauComponent,
    UtilisateurClassComponent,
    NewUtilisateurClassComponent,
    IdleWarningDialogComponent,
  ]
})
export class AdminLayoutModule {}

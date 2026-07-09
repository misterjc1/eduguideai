import { MonCompteComponent } from './components/mon-compte/mon-compte.component';
import { SimpleTreeViewComponent } from './layouts/simple-TreeView/simple-TreeView.component';
import { NewProfilComponent } from './components/profil/new-profil/new-profil.component';
import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatBadgeModule } from '@angular/material/badge';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRippleModule } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTooltipModule } from '@angular/material/tooltip';
import { RouterModule } from '@angular/router';
import { AdminLayoutRoutes } from 'app/layouts/admin-layout/admin-layout.routing';
import { TreeViewComponent } from './layouts/treeView/treeView.component';
import { PageComponent } from './components/page/page.component';
import { ProfilComponent } from './components/profil/profil.component';
import { NewUtilisateurComponent } from './components/utilisateur/new-utilisateur/new-utilisateur.component';
import { UtilisateurComponent } from './components/utilisateur/utilisateur.component';
import { UserModuleComponent } from './user-module.component';
import { DataTableComponent } from './layouts/data-table/data-table.component';
import { MatTreeModule } from '@angular/material/tree';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { NewPageComponent } from './components/page/new-page/new-page.component';
import { SessionComponent } from './components/session/session.component';
import { SessionDataTableComponent } from './layouts/session-data-table/session-data-table.component';
import { ConnexionComponent } from './components/connexion/connexion.component';
import { InscriptionComponent } from './components/inscription/inscription.component';
import { SimpleDataTableComponent } from './layouts/simple-data-table/simple-data-table.component';
import { AuditComponent } from './components/audit/audit.component';

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
    MatTooltipModule,
    MatDialogModule,
    MatTableModule,
    MatSortModule,
    MatSnackBarModule,
    MatRadioModule,
    MatPaginatorModule,
    MatInputModule,
    MatTabsModule,
    MatCheckboxModule,
    MatProgressSpinnerModule,
    MatBadgeModule,
    MatTreeModule,
    MatIconModule,
    MatDividerModule,
    MatExpansionModule
  ],
  declarations: [
    UserModuleComponent,
    DataTableComponent,
    SimpleDataTableComponent,
    ProfilComponent,
    PageComponent,
    UtilisateurComponent,
    NewUtilisateurComponent,
    NewProfilComponent,
    TreeViewComponent,
    SimpleTreeViewComponent,
    NewPageComponent,
    SessionComponent,
    SessionDataTableComponent,
    ConnexionComponent,
    InscriptionComponent,
    MonCompteComponent,
    AuditComponent
    
  ]
})
export class UserModuleModule { }

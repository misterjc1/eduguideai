import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import { PersonneService } from 'app/user-module/service/personne.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmComponent } from './../confirm/confirm.component';
import { Component, OnInit, ViewChild, Input, Output, EventEmitter } from '@angular/core';
import { DataTableMetaData, DataTableColonne } from '../datatable.model';
import { SelectionModel } from '@angular/cdk/collections';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { UtilisateurService } from 'app/user-module/service/utilisateur.service';
import { ProfilService } from 'app/user-module/service/profil.service';
import { NewProfilComponent } from 'app/user-module/components/profil/new-profil/new-profil.component';
import { NewUtilisateurComponent } from 'app/user-module/components/utilisateur/new-utilisateur/new-utilisateur.component';
import { DeleteVo } from 'app/user-module/class/DeleteVo';

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css']
})
export class DataTableComponent implements OnInit {

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  @Input() datatable: DataTableMetaData = new DataTableMetaData();
  @Input() size: string = '400px';
  @Output() messageEvent = new EventEmitter<any>();


  columnsToDisplay: string[] = [];
  headActionsToDisplay: string[] = [];
  rowActionsToDisplay: string[] = [];
  isSelection = false;
  selection = new SelectionModel<any>(true, []);
  data = new MatTableDataSource<any>();
  isDataLoaded: boolean = false;
  d: any = [{}];
  showSearch: boolean = false;
  showSelect: boolean = false;
  user: Utilisateur;
  userConnected: any = {};

  constructor(
    private _snackBar: MatSnackBar,
    private utilisateurService: UtilisateurService,
    private tokenService: TokenStorageService,
    private personneService: PersonneService,
    private profilService: ProfilService,
    public dialog: MatDialog) {
  }

  ngOnInit() {
    this.initDataTable();
    this.userConnected = this.tokenService.getUser();
  }


  initDataTable() {
    let pos: number; 
    this.datatable.colonnes.forEach((element,index) => {
      if (element.label === 'select') {
        this.showSelect = true
        pos = index
      }
        this.columnsToDisplay.push(element.label);
    });
    this.datatable.colonnes.splice(pos,1);
    this.headActionsToDisplay = this.datatable.headActions;
    this.rowActionsToDisplay = this.datatable.rowActions;

    if (this.rowActionsToDisplay.length > 0) {
      this.columnsToDisplay.push('rowActions');
    }
    this.data = new MatTableDataSource<any>(this.datatable.data);
    this.data.paginator = this.paginator;
    this.data.sort = this.sort;
    this.isDataLoaded = true;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.data.filter = filterValue.trim().toLowerCase();
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.data.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.data.data.forEach(row => this.selection.select(row));
  }

  /** The label for the checkbox on the passed row */
  checkboxLabel(row?: any): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.position + 1}`;
  }

  edit(row) {
    const value = row;
    switch (this.datatable.type) {
      case 'PERSONNE':
      case 'UTILISATEUR':
        this.dialog.open(NewUtilisateurComponent, { width: '1000px', data: value });
        break;
      case 'PROFIL':
        this.dialog.open(NewProfilComponent, { width: '900px', data: value });
        break;
      case 'USER':
        this.messageEvent.emit(value);
        break;
    }
  }

  add() {
    switch (this.datatable.type) {
      case 'PERSONNE':
      case 'UTILISATEUR':
        this.dialog.open(NewUtilisateurComponent, { width: '1000px' });
        break;
      case 'PROFIL':
        this.dialog.open(NewProfilComponent, { width: '900px' });
        break;
      case 'USER':
        this.messageEvent.emit('ADD');
        break;
    }
  }

  delete() {
    const deleteVo: DeleteVo = new DeleteVo();
    deleteVo.deleteDate = new Date();
    deleteVo.deleterCode = this.userConnected?.codeUtilisateur;

    switch (this.datatable.type) {
      case 'PERSONNE':
        this.selection.selected.forEach(ps => deleteVo.codes.push(ps.codePersonne));
        this.personneService.delete(deleteVo).subscribe(() => location.reload());
        break;
      case 'UTILISATEUR':
        this.selection.selected.forEach(u => deleteVo.codes.push(u.codeUtilisateur));
        this.utilisateurService.delete(deleteVo).subscribe(() => location.reload());
        break;
      case 'PROFIL':
        this.selection.selected.forEach(ps => deleteVo.codes.push(ps.codeProfil));
        this.profilService.delete(deleteVo).subscribe(() => location.reload());
        break;
    }
  }

    desactivate() {
      switch (this.datatable.type) {    
      }
    }

    openSnackBar(message: string, action: string) {
      this._snackBar.open(message, action);
    }

    upload() {
    }

    openConfirmDialog(libelleMsg: string): any {
      const dialogRef = this.dialog.open(ConfirmComponent, {
        width: '500px',
        data: {libelle: libelleMsg}
      });
      return dialogRef;

    }
}


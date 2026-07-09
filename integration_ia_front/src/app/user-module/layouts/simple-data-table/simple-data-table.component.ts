import { MatDialog } from '@angular/material/dialog';
import { ProfilService } from 'app/user-module/service/profil.service';
import { PersonneService } from 'app/user-module/service/personne.service';
import { UtilisateurService } from 'app/user-module/service/utilisateur.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NewUtilisateurComponent } from 'app/user-module/components/utilisateur/new-utilisateur/new-utilisateur.component';
import { Component, OnInit, ViewChild, Input, EventEmitter, Output } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { DataTableMetaData } from '../datatable.model';
import { SelectionModel } from '@angular/cdk/collections';
import { MatTableDataSource } from '@angular/material/table';
import { ConfirmComponent } from '../confirm/confirm.component';

@Component({
  selector: 'app-simple-data-table',
  templateUrl: './simple-data-table.component.html',
  styleUrls: ['./simple-data-table.component.css']
})
export class SimpleDataTableComponent implements OnInit {

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
  showSelect: boolean = false

  constructor(
    private _snackBar: MatSnackBar,
    private utilisateurService: UtilisateurService,
    private personneService: PersonneService,
    private profilService: ProfilService,
    public dialog: MatDialog) {
  }

  ngOnInit() {
    this.initDataTable();
  }


  initDataTable() {
    let pos: number;
    this.datatable.colonnes.forEach((element, index) => {
      if (element.label === 'select') {
        this.showSelect = true
        pos = index
      }
      this.columnsToDisplay.push(element.label);
    });
    this.datatable.colonnes.splice(pos, 1);
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
      case 'USER':
        this.messageEvent.emit(value)
        break;
    }
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  openConfirmDialog(libelleMsg: string): any {
    const dialogRef = this.dialog.open(ConfirmComponent, {
      width: '500px',
      data: { libelle: libelleMsg }
    });
    return dialogRef;

  }

  delete() {
    let data = this.data.data
    this.selection.selected.forEach(ps => {
      const index: number = data.indexOf(ps);
      if (index !== -1) {
        data.splice(index, 1);
      }
    });
    this.data.data = data;
  }



}

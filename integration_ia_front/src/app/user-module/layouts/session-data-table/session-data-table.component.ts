import { MatSnackBar } from '@angular/material/snack-bar';
import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { DataTableMetaData } from '../datatable.model';
import { SelectionModel } from '@angular/cdk/collections';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { UtilisateurService } from 'app/user-module/service/utilisateur.service';
import { ProfilService } from 'app/user-module/service/profil.service';

@Component({
  selector: 'app-session-data-table',
  templateUrl: './session-data-table.component.html',
  styleUrls: ['./session-data-table.component.css']
})
export class SessionDataTableComponent implements OnInit {

@ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  @Input() datatable: DataTableMetaData = new DataTableMetaData();


  columnsToDisplay: string[] = ['select'];
  selection = new SelectionModel<any>(true, []);
  data = new MatTableDataSource<any>();
  isDataLoaded: boolean = false;
  d: any = [{}];
  showSearch: boolean = false;

  constructor(
    private _snackBar: MatSnackBar,
    private utilisateurService: UtilisateurService,
    private profilService: ProfilService,
    public dialog: MatDialog) {
  }

  ngOnInit() {
    this.initDataTable();
  }

  initDataTable() {
    console.log('ok', this.datatable);
    this.datatable.colonnes.forEach(element => {
      this.columnsToDisplay.push(element.label);
    });
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

  AfficherSelection() {
    console.log('Mes cases sélectionnés ==>', this.selection.selected);
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
}

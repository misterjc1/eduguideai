import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DataTableMetaData } from 'app/layouts/datatable.model';
import { AuditService } from 'app/user-module/service/audit.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-audit',
  templateUrl: './audit.component.html',
  styleUrls: ['./audit.component.css']
})
export class AuditComponent implements OnInit {

  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any[] = [];
  errorMessage: string = '';

  constructor(private auditService: AuditService, private datePipe: DatePipe) {}

  ngOnInit(): void {
    this.auditService.findAll().pipe(
      catchError(() => {
        this.errorMessage = 'Le service d\'audit est momentanément indisponible.';
        return of({ payload: [] } as any);
      })
    ).subscribe((data: any) => {
      this.tabData = data?.payload || [];
      this.tabData.forEach(element => {
        if (element.date) {
          element.dateL = this.datePipe.transform(element.date, 'dd/MM/yyyy HH:mm');
        }
      });
      this.datatable.colonnes = [
        { label: 'select', name: 'select', show: true },
        { label: 'Auteur', name: 'auteur', show: true },
        { label: "Date de l'évènement", name: 'dateL', show: true },
        { label: 'Description', name: 'description', show: true },
      ];
      this.datatable.headActions = ['search'];
      this.datatable.rowActions = [];
      this.datatable.title = "Pistes d'audit";
      this.datatable.data = this.tabData;
      this.isDataLoaded = true;
      this.datatable.type = 'AUDIT';
    });
  }
}

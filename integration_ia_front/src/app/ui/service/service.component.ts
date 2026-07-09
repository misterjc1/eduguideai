import { Component, OnInit } from '@angular/core';
import { DataTableMetaData } from 'app/layouts/datatable.model';
import { ServiceService } from 'app/services/service.service';

@Component({
  selector: 'app-service',
  templateUrl: './service.component.html',
  styleUrls: ['./service.component.css']
})
export class ServiceComponent implements OnInit {

  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any = [{}];
  d: any;
  
  constructor(private serviceService: ServiceService) { }

  ngOnInit(): void {

    this.serviceService.findAll().subscribe(data => {
      this.tabData = data;
      this.tabData = this.tabData.payload;
      
      this.datatable.colonnes = [
        { label: 'select', name: 'select', show: true },
        { label: 'Type de Services', name: 'typeService', show: true },
        { label: 'Description', name: 'message', show: true },
        { label: 'Logo', name: 'logo', show: true },
        //{ label: 'TypeRequete', name: 'type', show: true },
        //{ label: 'Image', name: 'image', show: true },

  
      ];
      this.datatable.data = this.tabData; 
      this.datatable.headActions = ['search','add','delete']
      this.datatable.rowActions = ['edit']
      this.datatable.title = 'Liste des Services';
      this.datatable.type = 'SERVICE';
      this.isDataLoaded = true;
    });
  }
}


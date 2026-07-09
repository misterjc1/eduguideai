import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { StatutLiaison as Statut } from 'app/class/Enums/StatutLiaison';
import { DataTableMetaData } from 'app/layouts/datatable.model';
import { LiaisonService } from 'app/services/liaison.service';

@Component({
  selector: 'app-liaison',
  templateUrl: './liaison.component.html',
  styleUrls: ['./liaison.component.css']
})
export class LiaisonComponent implements OnInit {

  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any = [{}];
  d: any;

  constructor(private liaisonService: LiaisonService, private datePipe:DatePipe) { }

  ngOnInit(): void {

    this.liaisonService.findAll().subscribe(data => {
      this.tabData = data;
      this.tabData = this.tabData.payload;

      this.tabData.forEach(element => {
        /*if (element.dateDebut !== null) {
          element.dateDebutL = this.datePipe.transform(element.dateDebut,"dd/MM/yyyy HH:mm");
        }*/

        /*if (element.dateFin !== null) {
          element.dateFinL = this.datePipe.transform(element.dateFin,"dd/MM/yyyy HH:mm");
        }*/
        if (element.inscrit !== null) {
          element.inscritL = element.inscrit.matricule;
        } else {
          element.inscritL = "";
        }
        if (element.utilisateur !== null) {
          element.utilisateurL = element.utilisateur.nom + " " + element.utilisateur.prenom;
        } else {
          element.utilisateurL = "";
        }

        if (element.statutLiaison !== null) {
          element.statutLiaisonL = Statut[element.statutLiaison];
        }


      });
      
      this.datatable.colonnes = [
        { label: 'select', name: 'select', show: true },
        { label: 'Motif', name: 'motif', show: true },
        { label: 'Statut', name: 'statutLiaisonL', show: true },
        { label: 'Commentaire', name: 'commentaire', show: true },
        { label: 'Inscrit', name: 'inscritL', show: true },
        { label: 'Utilisateur', name: 'utilisateurL', show: true },

  
      ];
      this.datatable.data = this.tabData; 
      this.datatable.headActions = ['search','add','delete']
      this.datatable.rowActions = ['edit', 'view']
      this.datatable.title = 'Liste des liaisons';
      this.datatable.type = 'LIAISON';
      this.isDataLoaded = true;
    });

  }

}

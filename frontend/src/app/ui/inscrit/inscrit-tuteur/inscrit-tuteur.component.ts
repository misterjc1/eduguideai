import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Inscrit } from 'app/class/Inscrit';
import { Liaison } from 'app/class/Liaison';
import { UtilisateurClass } from 'app/class/UtilisateurClass';
import { DataTableMetaData } from 'app/layouts/datatable.model';
import { InscritService } from 'app/services/inscrit.service';
import { LiaisonService } from 'app/services/liaison.service';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-inscrit-tuteur',
  templateUrl: './inscrit-tuteur.component.html',
  styleUrls: ['./inscrit-tuteur.component.css']
})
export class InscritTuteurComponent implements OnInit {

  liaison: Liaison;
  middleData: any = [{}];
  inscrits: any = [{}];
  inscritsA: any = [{}];
  userConnected: any = {};
  isDataLoaded: boolean = false;
  public datatable = new DataTableMetaData();
  tabData: any = [{}];

  constructor(@Inject(MAT_DIALOG_DATA) public data: UtilisateurClass, public inscritService: InscritService,
  public liaisonService: LiaisonService,
  private tokenService: TokenStorageService) {
    this.liaison = new Liaison();
    this.liaison.inscrit = new Inscrit();
  }

  ngOnInit(): void {
    this.userConnected = this.tokenService.getUser();
    console.log("USERCODE", this.userConnected.codeUtilisateur);//Affiche le code de l'utilisateur connecté
    console.log("DATA", this.data); //Affiche les données de l'utilisateur, visible via la console du navigateur

    //Chargement des inscrits
    this.inscritService.findAll().subscribe(result => {
      this.middleData = result;
      this.inscrits = this.middleData.payload;

      console.log("Les inscrits: ", this.inscrits);

      if (!this.liaison.inscrit) {
        this.liaison.inscrit = new Inscrit();
      }

      

    });
    if (this.data) {
      console.log("Les tuteurs: ", this.data);
      this.liaisonService.findLiaisonByCodeUtilisateur(this.data.codeUtilisateur, 'VALIDE').subscribe(result => {
        this.middleData = result;
        this.inscritsA = this.middleData.payload;
        console.log("inscritsA :", this.inscritsA);
        this.tabData = this.inscritsA.map(element => {
          // if (element.inscrit) {
          //   element.matricule = element.inscrit.matricule;
          //   //element.motif = element.inscrit.motif;
          // }
          if(element.liaison) {
            element.motif = element.liaison.motif;
          }
          else {
            //element.matricule = 'N/A';  // Valeur par défaut si `inscrit` est null ou undefined
            element.motif = 'N/A';
          }
          return element;
  
        });
          this.datatable.colonnes = [
            { label: 'select', name: 'select', show: true },
            { label: 'Matricule', name: 'matricule', show: true },
            { label: 'Motif', name: 'motif', show: true },
            { label: 'Nom', name: 'nom', show: true },
            { label: 'Prénom', name: 'prenom', show: true },
      
          ];
          console.log("tabData:", this.tabData);
          this.datatable.data = this.tabData; 
          this.datatable.headActions = ['search','delete']
          this.datatable.rowActions = ['edit']
          this.datatable.title = 'Liste des inscrits';
          this.datatable.type = 'INSCRIT';
          this.isDataLoaded = true;
        
      })

    }
    console.log("Résultat des inscrits assignés:", this.inscritsA);
  }

  onSubmit(){
    this.liaison.utilisateur = this.data;
    this.liaison.creatorCode = this.userConnected.codeUtilisateur;
    console.log("La liaison", this.liaison);

    // Recherche de l'inscrit correspondant au matricule entré
    const inscritCorrespondant = this.inscrits.find((inscrit: any) => inscrit.matricule === this.liaison.inscrit.matricule);

    if (!inscritCorrespondant) {
      this.alerter('Erreur', 'error', 'Aucun inscrit trouvé avec ce matricule');
      return;
    }

    // Associer l'inscrit trouvé à la liaison
    this.liaison.inscrit = inscritCorrespondant;
    this.liaison.utilisateur = this.data;
    this.liaison.creatorCode = this.userConnected.codeUtilisateur;


    this.liaisonService.save(this.liaison).subscribe(result => {
      this.middleData = result;
      if (this.middleData.status === 'OK') {
        this.alerter('Success !', 'success', this.middleData.message);
      } else {
        this.alerter('Erreur !', 'error', this.middleData.message);
      }
    },
    error => {
      this.alerter('Erreur !', 'error', ' Impossible de contacter le serveur !');
    })
    
  }
    alerter(message, icon, texte){
      Swal.fire({
        title: message,
        text: texte,
        icon: icon,
        showCancelButton: false,
        confirmButtonColor: '#3085d6',
        confirmButtonText: 'Ok'
      }).then((result) => {
        if (result.isConfirmed) {
          location.reload();
        }
      })
  
    }


}

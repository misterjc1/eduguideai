import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Inscrit } from 'app/class/Inscrit';
import { Niveau } from 'app/class/Niveau';
import { EnumService } from 'app/services/Enum.service';
import { InscritService } from 'app/services/inscrit.service';
import { NiveauService } from 'app/services/niveau.service';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-inscrit',
  templateUrl: './new-inscrit.component.html',
  styleUrls: ['./new-inscrit.component.css']
})
export class NewInscritComponent implements OnInit {

  inscrit: Inscrit;
  update: boolean = false;
  d: any = [{}];
  error: boolean = false;
  errorMessage: string = '';
  url;
  msg = '';
  user: Utilisateur;
  userConnected: any = {};
  middleData: any = [{}];
  niveaux: any = [{}];

  constructor(
    @Optional() @Inject(MAT_DIALOG_DATA) public data: Inscrit,
    private dialogRef: MatDialogRef<NewInscritComponent>,
    private niveauService: NiveauService,
    private tokenService: TokenStorageService,
    private _snackBar: MatSnackBar,
    private inscritService: InscritService,
    private enumService: EnumService
  ) {
    this.inscrit = new Inscrit();
    this.inscrit.niveau = new Niveau()
  }

  ngOnInit(): void {
    this.userConnected = this.tokenService.getUser();
    console.log("USERCODE", this.userConnected.codeUtilisateur);//Affiche le code de l'utilisateur connecté
    console.log("DATA", this.data); //Affiche les données de l'utilisateur, visible via la console du navigateur

    this.niveauService.findAll().subscribe(data => {
      this.middleData = data;
      this.niveaux = this.middleData.payload;
      
    });

    if (this.data) {
      this.update  = true;
      this.inscrit = this.data;

      if (!this.inscrit.niveau) {
        this.inscrit.niveau = new Niveau();
      }

    }
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  closeError(): void {
    this.error = false;
    this.errorMessage = '';
  }

//Fonction pour verifier les saisies de l'utilisateur
verifierSaisie(): boolean {
  //const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  console.log(this.inscrit);

  if (this.inscrit.matricule === undefined || this.inscrit.nom === undefined
    || this.inscrit.prenom === undefined || this.inscrit.telephone === undefined || this.inscrit.email === undefined) {
    this.openSnackBar('Veuillez remplir les champs obligatoires (*) !', 'Ok');
    return false;
  } else {
    return true;
  }
}

  onSubmit(): void {
  if (this.verifierSaisie()) {
    if (this.update) {
      // MAJ en BD
      this.inscrit.updateDate = new Date();
      this.inscrit.updaterCode = this.userConnected.codeUtilisateur;
      this.inscritService.update(this.inscrit).subscribe(
        result => {
          this.d = result;
          if (this.d.status === 'OK') {
            this.alerter('Success !', 'success', this.d.message);
          } else {
            this.alerter('Erreur !', 'error', this.d.message);
          }
        },
        error => {
          this.alerter('Erreur !', 'error', ' Impossible de contacter le serveur !');
        }
      );
      
    } else {
      // Ajout en Butilisateur
      this.inscrit.creationDate = new Date();
      this.inscrit.creatorCode = this.userConnected.codeUtilisateur;
      console.log(this.inscrit);
      this.inscrit.creationDate = new Date();
      this.inscrit.deleted = false;

            this.niveauService.findByCode(this.inscrit.niveau.codeNiveau).subscribe(
              result1 => {
                this.middleData = result1;
                if (this.middleData.status === 'OK') {
                  console.log("middleData[0]", this.middleData.payload[0]);
                  this.inscrit.niveau = this.middleData.payload[0];
                  console.log(this.inscrit);
                         this.inscritService.save(this.inscrit).subscribe(
                          result2 => {
                            this.middleData = result2;
                            if (this.middleData.status === 'OK') {
                              this.alerter('Success !', 'success', this.middleData.message);
                            } else {
                              this.alerter('Erreur !', 'error', this.middleData.message);
                            }
                          },
                          error => {
                            this.alerter('Erreur !', 'error', ' Impossible de contacter le serveur !');
                          }
                        );
                  
                } else {
                  this.alerter('Erreur !', 'error', this.middleData.message);
                }
              },
              error => {
                this.alerter('Erreur !', 'error', ' Impossible de contacter le serveur !');
              }
            );
          } 
    }
  }

  alerter(message, icon, texte) {
    Swal.fire({
      title: message,
      text: texte,
      icon: icon,
      showCancelButton: false,
      confirmButtonColor: '#3085d6',
      confirmButtonText: 'Ok'
    }).then((result) => {
      if (result.isConfirmed) {
        this.dialogRef.close(true);
        location.reload();
      }
    });
  }
}

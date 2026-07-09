import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EnumVo } from 'app/class/EnumVo';
import { Inscrit } from 'app/class/Inscrit';
import { Liaison } from 'app/class/Liaison';
import { UtilisateurClass } from 'app/class/UtilisateurClass';
import { EnumService } from 'app/services/Enum.service';
import { InscritService } from 'app/services/inscrit.service';
import { LiaisonService } from 'app/services/liaison.service';
import { UtilisateurClassService } from 'app/services/utilisateur-class.service';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-liaison',
  templateUrl: './new-liaison.component.html',
  styleUrls: ['./new-liaison.component.css']
})
export class NewLiaisonComponent implements OnInit {

  liaison: Liaison;
  update: boolean = false;
  middleData: any = [{}];
  d: any = [{}];
  error: boolean = false;
  errorMessage: string = '';
  url;
  msg = '';
  user: Utilisateur;
  userConnected: any = {};
  statutLiais: EnumVo[] = [];
  utilisateurs: any = [{}];
  inscrits: any = [{}];


  constructor(
    @Optional() @Inject(MAT_DIALOG_DATA) public data: Liaison,
    private dialogRef: MatDialogRef<NewLiaisonComponent>,
    private inscritService: InscritService,
    private utilisateurClassService: UtilisateurClassService,
    private tokenService: TokenStorageService,
    private _snackBar: MatSnackBar,
    private liaisonService: LiaisonService,
    private enumService: EnumService
  ) {
    this.liaison = new Liaison();
    this.liaison.inscrit = new Inscrit();
    this.liaison.utilisateur = new UtilisateurClass();
  }


  ngOnInit(): void {

    this.userConnected = this.tokenService.getUser();
    console.log("USERCODE", this.userConnected.codeUtilisateur);//Affiche le code de l'utilisateur connecté
    console.log("DATA", this.data); //Affiche les données de l'utilisateur, visible via la console du navigateur

    this.inscritService.findAll().subscribe(data => {
      this.middleData = data;
      this.inscrits = this.middleData.payload;
      
    });
    this.utilisateurClassService.findAll().subscribe(data => {
      this.middleData = data;
      this.utilisateurs = this.middleData.payload;
      
    });

    if (this.data) {
      this.update  = true;
      this.liaison = this.data;
      console.log("La liaison est :", this.liaison);

      if (!this.liaison.inscrit) {
        this.liaison.inscrit = new Inscrit();
      }

      
      if (!this.liaison.utilisateur) {
        this.liaison.utilisateur = new UtilisateurClass();
      }

    }

    this.enumService.findAllStatutLiaison().subscribe(
      result => {
        this.statutLiais = result.payload;
      }
    );


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
  console.log(this.liaison);

  if (this.liaison.motif === undefined || this.liaison.statutLiaison === undefined
    || this.liaison.commentaire === undefined) {
    this.openSnackBar('Veuillez remplir les champs obligatoires (*) !', 'Ok');
    return false;
  } else {
    return true;
  }
}

  onSubmit() : void {if (this.verifierSaisie()) {
    if (this.update) {
      // MAJ en BD
      this.liaison.updateDate = new Date();
      this.liaison.updaterCode = this.userConnected.codeUtilisateur;
      
      // Mettre à jour l'inscrit et l'utilisateur
      this.updateInscritAndUtilisateur(() => {
        this.liaisonService.update(this.liaison).subscribe(
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
      });
    } else {
          //Ajout en BD
      this.liaison.creationDate = new Date();
      this.liaison.deleted = false;
      this.liaison.creatorCode = this.userConnected.codeUtilisateur;
      //this.liaison.statut = "EN_COURS";
      console.log(this.liaison);
      this.inscritService.findByCode(this.liaison.inscrit.codeInscrit).subscribe(
        result1 => {
          this.middleData = result1;
          if (this.middleData.status === 'OK') {
            this.liaison.inscrit = this.middleData.payload[0];
            this.utilisateurClassService.findByCode(this.liaison.utilisateur.codeUtilisateur).subscribe(
              result2 => {
                this.middleData = result2;
                if (this.middleData.status === 'OK') {
                  this.liaison.utilisateur = this.middleData.payload[0];
                  this.liaisonService.save(this.liaison).subscribe(
                    result3 => {
                      this.middleData = result3;
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
                  //this.alerter('Success !', 'success', this.middleData.message);
                } else {
                  this.alerter('Erreur !', 'error', this.middleData.message);
                }
              },
              error => {
                this.alerter('Erreur !', 'error', ' Impossible de contacter le serveur !');
              }
            );
            //this.alerter('Success !', 'success', this.middleData.message);
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

  private updateInscritAndUtilisateur(callback: () => void): void {
    this.inscritService.findByCode(this.liaison.inscrit.codeInscrit).subscribe(
      result1 => {
        this.middleData = result1;
        if (this.middleData.status === 'OK') {
          this.liaison.inscrit = this.middleData.payload[0];
          this.utilisateurClassService.findByCode(this.liaison.utilisateur.codeUtilisateur).subscribe(
            result2 => {
              this.middleData = result2;
              if (this.middleData.status === 'OK') {
                this.liaison.utilisateur = this.middleData.payload[0];
                callback();
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

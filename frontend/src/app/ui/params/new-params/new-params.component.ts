import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Parametre } from 'app/class/Parametre';
import { ParametreService } from 'app/services/parametre.service';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-params',
  templateUrl: './new-params.component.html',
  styleUrls: ['./new-params.component.css']
})
export class NewParamsComponent implements OnInit {

  parametre: Parametre;
  update: boolean = false;
  d: any = {};
  middleData: any = [{}];
  url;
  msg = '';
  error: boolean = false;
  errorMessage: string = '';
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  user: Utilisateur;
  userConnected: any = {};

  constructor(@Inject(MAT_DIALOG_DATA) public data: Parametre,
    private parametreService: ParametreService,
    private tokenService: TokenStorageService,
    private _snackBar: MatSnackBar) {
    this.parametre = new Parametre();
    }
  ngOnInit(): void {
    this.userConnected = this.tokenService.getUser();
    console.log("USERCODE", this.userConnected.codeUtilisateur);
    console.log("DATA", this.data);
    if (this.data) {
      
      this.update = true;
      this.parametre = this.data;


    }
  }
  onSubmit(): void {
    if (this.verifierSaisie()) {

      if (this.update) {
        // MAJ en BD
        this.parametre.updateDate = new Date();
        this.parametre.updaterCode = this.userConnected.codeUtilisateur;
        this.parametreService.update(this.parametre).subscribe(result => {
          this.d = result;
          if (this.d.status === 'OK') {
            this.alerter('Success !', 'success', this.d.message);
            //this.openSnackBar(this.d.message, 'Ok');
            //this.reload();
          } else {
            //this.openSnackBar(this.d.message, 'Retour');
             this.alerter('Erreur !', 'error', this.d.message);
          }
        },
          error => {
            //this.error = true;
            //this.errorMessage = 'Impossible de contacter le serveur !';
            this.alerter('Erreur !', 'error', ' Impossible de contacter le serveur !');
          });
      } else {
        // Ajout en BD
        console.log(this.parametre);
        this.parametre.creationDate = new Date();
        this.parametre.deleted = false;
        this.parametre.creatorCode = this.userConnected.codeUtilisateur;
        this.parametreService.save(this.parametre).subscribe(result => {
          this.d = result;
          if (this.d.status === 'OK') {
            this.alerter('Success !', 'success', this.d.message);
            //this.openSnackBar(this.d.message, 'Ok');
            //this.reload();
          } else {
            this.alerter('Erreur !', 'error', this.d.message);
            //this.openSnackBar(this.d.message, 'Retour');
          }
        },
          error => {
            this.alerter('Erreur !', 'error', ' Impossible de contacter le serveur !');
           // this.error = true;
            //this.errorMessage = 'Impossible de contacter le serveur !';
          });
      }
    }
  }

   /*reload() {
    location.reload();
  }*/

  verifierSaisie(): boolean {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    console.log(this.parametre);

    if (this.parametre.parametres === undefined ) {
      this.openSnackBar('Veuillez remplir les champs obligatoires (*) !', 'Ok');
      return false;
    } else {
      return true;
    }
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  selectFile(event) {
    if (!event.target.files[0] || event.target.files[0].length == 0) {
      this.msg = 'You must select an image';
      return;
    }

    var mimeType = event.target.files[0].type;

    if (mimeType.match(/image\/*/) == null) {
      this.msg = 'Only images are supported';
      return;
    }

    var reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);

    /*reader.onload = (_event) => {
      this.msg = '';
      this.url = reader.result;
      this.structure.logo = this.url;
      this.structure.logo = this.structure.logo.split(',')[1];
    }*/
  }

 /* closeError() {
    this.error = false;
  }*/


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


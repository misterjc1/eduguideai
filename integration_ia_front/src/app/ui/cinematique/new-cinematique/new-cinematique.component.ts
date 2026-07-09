import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Cinematique } from 'app/class/Cinematique';
import { TypeService } from 'app/class/Enums/TypeService';
import { Service } from 'app/class/Service';
import { TypeCinematique } from 'app/class/TypeCinematique';
import { CinematiqueService } from 'app/services/cinematique.service';
import { ServiceService } from 'app/services/service.service';
import { TypeCinematiqueService } from 'app/services/type-cinematique.service';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-cinematique',
  templateUrl: './new-cinematique.component.html',
  styleUrls: ['./new-cinematique.component.css']
})
export class NewCinematiqueComponent implements OnInit {

  cinematique: Cinematique;
  update: boolean = false;
  d: any = {};
  middleData: any = [{}];
  typescinematique: any = [{}];
  typService: any = [{}];
  //utilisateurs: any = [{}];
  url;
  msg = '';
  error: boolean = false;
  errorMessage: string = '';
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  user: Utilisateur;
  userConnected: any = {};


  constructor(@Inject(MAT_DIALOG_DATA) public data: Cinematique,
    private cinematiqueService: CinematiqueService,
    private typeCinematiqueService: TypeCinematiqueService,
    private serviceService: ServiceService,
    private tokenService: TokenStorageService,
    private _snackBar: MatSnackBar) {
    this.cinematique = new Cinematique();
    this.cinematique.typeCinematique = new TypeCinematique();
    this.cinematique.typeService = new Service();

    }
  ngOnInit(): void {
    this.userConnected = this.tokenService.getUser();
    console.log("USERCODE", this.userConnected.codeUtilisateur);
    console.log("DATA", this.data);

    this.typeCinematiqueService.findAll().subscribe(data => {
      this.middleData = data;
      this.typescinematique = this.middleData.payload;
    });


    this.serviceService.findAll().subscribe(data => {
      this.middleData = data;
      this.typService = this.middleData.payload;
    });

    //autres
    if (this.data) {
      this.update = true;
      this.cinematique = this.data;

      if (!this.cinematique.typeCinematique) {
        this.cinematique.typeCinematique = new TypeCinematique();
      }
      console.log(this.cinematique)
    }

    if (this.data) {
      this.update = true;
      this.cinematique = this.data;

      if (!this.cinematique.typeService) {
        this.cinematique.typeService = new Service();
      }
      console.log(this.cinematique)
    }
  }


  onSubmit(): void {
    if (this.verifierSaisie()) {

      if (this.update) {
        // MAJ en BD
        this.cinematique.updateDate = new Date();
        this.cinematique.updaterCode = this.userConnected.codeUtilisateur;
        this.cinematiqueService.update(this.cinematique).subscribe(result => {
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
        console.log(this.cinematique);
        this.cinematique.creationDate = new Date();
        this.cinematique.deleted = false;
        this.cinematique.creatorCode = this.userConnected.codeUtilisateur;
        this.cinematiqueService.save(this.cinematique).subscribe(result => {
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
    console.log(this.cinematique);

    if (this.cinematique.intitule === undefined ) {
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

    reader.onload = (_event) => {
      this.msg = '';
      this.url = reader.result;
      this.cinematique.image = this.url;
      this.cinematique.image = this.cinematique.image.split(',')[1];
    }
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
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EnumVo } from 'app/class/EnumVo';
import { Service } from 'app/class/Service';
import { EnumService } from 'app/services/Enum.service';
import { ServiceService } from 'app/services/service.service';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-service',
  templateUrl: './new-service.component.html',
  styleUrls: ['./new-service.component.css']
})
export class NewServiceComponent implements OnInit {

  service: Service;
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
  serv: EnumVo[] = [];


  constructor(@Inject(MAT_DIALOG_DATA) public data: Service,
    private serviceService: ServiceService,
    private enumService: EnumService,
    private tokenService: TokenStorageService,
    private _snackBar: MatSnackBar) {
    this.service = new Service();
    }
  ngOnInit(): void {
    this.userConnected = this.tokenService.getUser();
    console.log("USERCODE", this.userConnected.codeUtilisateur);
    console.log("DATA", this.data);

    this.enumService.findAllTypeServices().subscribe( 
      result => {
        this.serv = result.payload;
      }
    );
    
    if (this.data) {
      
      this.update = true;
      this.service = this.data;

      if (this.service.logo) {
        this.serviceService.findLogo(this.service.logo).subscribe((element) => {
          this.d = element;
          this.url = "data:image/png;base64," + this.d.payload;
        });
        console.log("URL", this.url);
      }

    }
  }
  onSubmit(): void {
    if (this.verifierSaisie()) {

      if (this.update) {
        // MAJ en BD
        this.service.updateDate = new Date();
        this.service.updaterCode = this.userConnected.codeUtilisateur;
        this.serviceService.update(this.service).subscribe(result => {
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
        console.log(this.service);
        this.service.creationDate = new Date();
        this.service.deleted = false;
        this.service.creatorCode = this.userConnected.codeUtilisateur;
        this.serviceService.save(this.service).subscribe(result => {
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
    console.log(this.service);

    if (this.service.typeService === undefined || this.service.message === undefined
      ) {
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
      this.service.logo = this.url;
      this.service.logo = this.service.logo.split(',')[1];
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


import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Niveau } from 'app/class/Niveau';
import { NiveauService } from 'app/services/niveau.service';
import { NotificationService } from 'app/services/notification.service';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-niveau',
  templateUrl: './new-niveau.component.html',
  styleUrls: ['./new-niveau.component.css']
})
export class NewNiveauComponent implements OnInit {

  niveau: Niveau;
  update: boolean = false;
  middleData: any = [{}];
  url;
  msg = '';
  user: Utilisateur;
  userConnected: any = {};
  error: boolean = false;
  errorMessage: string = '';

  constructor(
    @Optional() @Inject(MAT_DIALOG_DATA) public data: Niveau,
    private dialogRef: MatDialogRef<NewNiveauComponent>,
    private tokenService: TokenStorageService,
    private niveauService: NiveauService,
    private notificationService: NotificationService
  ) {
    this.niveau = new Niveau();
  }
   ngOnInit(): void {
    this.userConnected = this.tokenService.getUser();
    if (this.data) {
      this.update  = true;
      this.niveau = this.data;
    }
  }

  onSubmit(): void {
    if (this.update) {
      // MAJ en BD
      this.niveau.updateDate = new Date();
        this.niveau.updaterCode = this.userConnected.codeUtilisateur;
      this.niveauService.update(this.niveau).subscribe(
        result => {
          this.middleData = result;
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
      // Ajout en BD
      this.niveau.creationDate = new Date();
      this.niveau.creatorCode = this.userConnected.codeUtilisateur;
      console.log(this.niveau);
      this.niveau.creationDate = new Date();
      this.niveau.deleted = false;
      this.niveauService.save(this.niveau).subscribe(
        result => {
          this.middleData = result;
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
    }
  }

  closeError(): void {
    this.error = false;
    this.errorMessage = '';
  }

  alerter(message, icon, texte) {
    const notifType = icon === 'success' ? 'success' : icon === 'error' ? 'error' : 'warning';
    this.notificationService.add(message, texte, notifType);
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

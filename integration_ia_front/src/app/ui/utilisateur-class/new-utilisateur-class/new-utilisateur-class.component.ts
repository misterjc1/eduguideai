import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EnumVo } from 'app/class/EnumVo';
import { UtilisateurClass } from 'app/class/UtilisateurClass';
import { EnumService } from 'app/services/Enum.service';
import { NotificationService } from 'app/services/notification.service';
import { UtilisateurClassService } from 'app/services/utilisateur-class.service';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-utilisateur-class',
  templateUrl: './new-utilisateur-class.component.html',
  styleUrls: ['./new-utilisateur-class.component.css']
})
export class NewUtilisateurClassComponent implements OnInit {

  utilisateur: UtilisateurClass;
  update: boolean = false;
  d: any = [{}];
  middleData: any = [{}];
  error: boolean = false;
  errorMessage: string = '';
  url;
  msg = '';
  user: Utilisateur;
  userConnected: any = {};
  typeUtilis: EnumVo[] = [];

  constructor(
    @Optional() @Inject(MAT_DIALOG_DATA) public data: UtilisateurClass,
    private dialogRef: MatDialogRef<NewUtilisateurClassComponent>,
    private tokenService: TokenStorageService,
    private _snackBar: MatSnackBar,
    private utilisateurClassService: UtilisateurClassService,
    private enumService: EnumService,
    private notificationService: NotificationService
  ) {
    this.utilisateur = new UtilisateurClass();
  }

  ngOnInit(): void {
    this.userConnected = this.tokenService.getUser();

    this.enumService.findAllTypeUtilisateur().subscribe(
      result => {
        this.typeUtilis = result.payload;
      }
    );

    if (this.data) {
      this.update = true;
      this.utilisateur = Object.assign(new UtilisateurClass(), this.data);
    }
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  closeError(): void {
    this.error = false;
    this.errorMessage = '';
  }

  verifierSaisie(): boolean {
    if (!this.utilisateur.username) {
      this.openSnackBar('Veuillez saisir un nom d\'utilisateur !', 'Ok');
      return false;
    }
    if (!this.update && !this.utilisateur.password) {
      this.openSnackBar('Veuillez saisir un mot de passe !', 'Ok');
      return false;
    }
    return true;
  }

  onSubmit(): void {
    if (this.verifierSaisie()) {
      if (this.update) {
        this.utilisateur.updateDate = new Date();
        this.utilisateur.updaterCode = this.userConnected.codeUtilisateur;
        // Ne pas envoyer password null (l'API le masque en null sur findAll)
        // Le backend UtilisateurController.update() préserve le mot de passe existant si null
        const payload: any = Object.assign({}, this.utilisateur);
        if (!payload.password) delete payload.password;
        this.utilisateurClassService.update(payload).subscribe(
          result => {
            this.d = result;
            if (this.d.status === 'OK') {
              this.alerter('Succès !', 'success', this.d.message);
            } else {
              this.alerter('Erreur !', 'error', this.d.message);
            }
          },
          error => {
            this.alerter('Erreur !', 'error', 'Impossible de contacter le serveur !');
          }
        );
      } else {
        this.utilisateur.creationDate = new Date();
        this.utilisateur.creatorCode = this.userConnected.codeUtilisateur;
        this.utilisateur.deleted = false;
        this.utilisateurClassService.save(this.utilisateur).subscribe(
          result => {
            this.d = result;
            if (this.d.status === 'OK') {
              this.alerter('Succès !', 'success', this.d.message);
            } else {
              this.alerter('Erreur !', 'error', this.d.message);
            }
          },
          error => {
            this.alerter('Erreur !', 'error', 'Impossible de contacter le serveur !');
          }
        );
      }
    }
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

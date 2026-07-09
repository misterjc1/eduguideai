import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { Inscrit } from 'app/class/Inscrit';
import { Liaison } from 'app/class/Liaison';
import { UtilisateurClass } from 'app/class/UtilisateurClass';
import { LiaisonService } from 'app/services/liaison.service';
import { NewUtilisateurClassComponent } from 'app/ui/utilisateur-class/new-utilisateur-class/new-utilisateur-class.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-liaison-details',
  templateUrl: './liaison-details.component.html',
  styleUrls: ['./liaison-details.component.css']
})
export class LiaisonDetailsComponent implements OnInit {

  panelOpenState = false;

  liaison: Liaison;
  update: boolean = false;
  middleData: any = [{}];
  signatureBase64 : any;

  msg = '';
  constructor(@Inject(MAT_DIALOG_DATA) public data: Liaison,
  private liaisonService: LiaisonService, private _sanitizer: DomSanitizer, 
  public dialog: MatDialog) {
    this.liaison = new Liaison();
    this.liaison.inscrit = new Inscrit();
    this.liaison.utilisateur = new UtilisateurClass();
   }


   ngOnInit(): void {

    this.update  = true;
    this.liaison = this.data;
}

    width = window.screen.width;

    openStructure(value){
      this.dialog.open(NewUtilisateurClassComponent, {
        width: `${this.width}px`,
        data: value
      });
    }

    onSubmit(): void {
      // MAJ en BD
      //this.liaison.statut = 'CLOTURE';
      this.liaison.synchronizationDate = new Date;
      //this.visite.updaterCode = 
      this.liaisonService.update(this.liaison).subscribe(
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

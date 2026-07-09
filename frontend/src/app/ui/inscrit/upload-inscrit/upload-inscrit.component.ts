import { Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Inscrit } from 'app/class/Inscrit';
import { Niveau } from 'app/class/Niveau';
import { DataTableMetaData } from 'app/layouts/datatable.model';
import { InscritService } from 'app/services/inscrit.service';
import { NiveauService } from 'app/services/niveau.service';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import Swal from 'sweetalert2';
import * as XLSX from 'xlsx';


@Component({
  selector: 'app-upload-inscrit',
  templateUrl: './upload-inscrit.component.html',
  styleUrls: ['./upload-inscrit.component.css']
})
export class UploadInscritComponent implements OnInit {

  file: any;
  arrayBuffer: any;
  //
  update: boolean = false;
  middleData: any = [{}];
  niveaux: any = [{}];
  inscrits: any[];
  inscrit: Inscrit;
  d: any = [{}];
  isUpload: boolean;
  error: boolean = false;
  errorMessage: string = "";
  selectedNiveau: Niveau;
  user: Utilisateur;
  userConnected: any = {};

  public datatable = new DataTableMetaData();
  isDataLoaded: boolean = false;
  tabData: any = [{}];

  fileAttr = 'Selectionner un fichier';
  @ViewChild('fileInput') fileInput: ElementRef;

  uploadFileEvt(imgFile: any) {
    if (imgFile.target.files && imgFile.target.files[0]) {
      this.fileAttr = '';
      Array.from(imgFile.target.files).forEach((file: any) => {
        this.fileAttr += file.name + ' - ';
      });
      // HTML5 FileReader API
      let reader = new FileReader();
      reader.onload = (e: any) => {
        let image = new Image();
        image.src = e.target.result;
        image.onload = (rs) => {
          let imgBase64Path = e.target.result;
        };
      };
      reader.readAsDataURL(imgFile.target.files[0]);
      // Reset if duplicate image uploaded again
      this.fileInput.nativeElement.value = '';
    } else {
      this.fileAttr = 'Choose File';
    }
  }

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: Inscrit,
    private niveauService: NiveauService,
    private inscritService: InscritService,
    private tokenService: TokenStorageService,
    private _snackBar: MatSnackBar
  ) {
    this.inscrit = new Inscrit();
    this.inscrit.niveau = new Niveau();
  }

  ngOnInit() {
    this.isUpload = false;
    this.userConnected = this.tokenService.getUser();

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

  onFileChange(evt: any) {
    const target: DataTransfer = <DataTransfer>evt.target;
    if (target.files.length !== 1)
      throw new Error("Impossible upload plusieurs fichiers");
    const reader: FileReader = new FileReader();

    reader.onload = (e: any) => {
      const bstr: string = e.target.result;
      const wb: XLSX.WorkBook = XLSX.read(bstr, { type: "binary" });
      const wsname: string = wb.SheetNames[0];
      const ws: XLSX.WorkSheet = wb.Sheets[wsname];
      this.inscrits = XLSX.utils.sheet_to_json(ws, { header: 1 });
      this.isUpload = true;
    };
    reader.readAsBinaryString(target.files[0]);
  }

  Upload(event: any) {
    if (event.target.files && event.target.files[0]) {
        this.fileAttr = event.target.files[0].name;
        this.file = event.target.files[0];
        const fileReader = new FileReader();
        fileReader.onload = (e) => {
            this.arrayBuffer = fileReader.result;
            const data = new Uint8Array(this.arrayBuffer);
            const arr = new Array();
            for (let i = 0; i !== data.length; ++i)
                arr[i] = String.fromCharCode(data[i]);
            const bstr = arr.join('');
            const workbook = XLSX.read(bstr, { type: 'binary' });
            const first_sheet_name = workbook.SheetNames[0];
            const worksheet = workbook.Sheets[first_sheet_name];
            console.log(XLSX.utils.sheet_to_json(worksheet, { raw: true }));

            this.inscrits = XLSX.utils.sheet_to_json(worksheet, {
                raw: true,
            }) as any[];

            // Assigner le niveau sélectionné à chaque inscrit importé
            this.inscrits.forEach(inscrit => {
              inscrit.niveau = { codeNiveau: this.inscrit.niveau.codeNiveau } as Niveau; // Appliquer le niveau sélectionné
          });

            this.datatable.colonnes = [
                { label: 'select', name: 'select', show: true },
                { label: 'Matricule', name: 'matricule', show: true },
                { label: 'Nom', name: 'nom', show: true },
                { label: 'Prénom', name: 'prenom', show: true },
                { label: 'email', name: 'email', show: true },
                { label: 'Téléphone', name: 'telephone', show: true },
               // { label: 'Niveau', name: 'niveauL', show: true }
            ];
            this.datatable.headActions = ['search'];
            this.datatable.rowActions = [];
            this.datatable.title = 'Liste des Inscrits';
            this.datatable.data = this.inscrits;
            this.datatable.type = 'INSCRIT';
            this.isDataLoaded = true;
            this.isUpload = true;
        };
        console.log("INSCRIT", this.inscrits);
        fileReader.readAsArrayBuffer(this.file);
        this.isUpload = true;
    } else {
        this.fileAttr = 'Choose File';
    }
}

  saveAllInscrits(){
    return new Promise<void>((resolve, reject) => {
      this.userConnected = this.tokenService.getUser();
    this.inscrits.forEach((element) => {
      this.inscrit = element;
      this.inscrit.creationDate = new Date();
      this.inscrit.creatorCode = this.userConnected.codeUtilisateur;
      this.inscrit.niveau = this.selectedNiveau; 
      this.inscritService.save(this.inscrit).subscribe(
        (result) => {
          this.d = result;
          if (this.d.status === "OK") {
            this.alerter('Success !', 'success', this.d.message);
          } else {
            this.alerter('Erreur !', 'error', this.d.message);
          }
        },
        (error) => {
          this.alerter('Erreur !', 'error', ' Impossible de contacter le serveur !');
        }
      );

    });
      resolve();
  });
  }

  onSubmit(): void {
    if (!this.selectedNiveau) {
      this.alerter('Erreur !', 'error', 'Veuillez sélectionner un niveau avant d\'importer.');
      return;
    }
    this.saveAllInscrits();
  }

  reload() {
    location.reload();
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  closeError() {
    this.error = false;
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
        location.reload();
      }
    })
  }

}

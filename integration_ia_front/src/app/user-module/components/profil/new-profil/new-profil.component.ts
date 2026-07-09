import { SimpleTreeViewComponent } from './../../../layouts/simple-TreeView/simple-TreeView.component';
import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Page } from 'app/user-module/class/Page';
import { Profil } from 'app/user-module/class/Profil';
import { TreeViewMetaData } from 'app/user-module/layouts/treeView.model';
import { PageService } from 'app/user-module/service/page.service';
import { ProfilService } from 'app/user-module/service/profil.service';

@Component({
  selector: 'app-new-profil',
  templateUrl: './new-profil.component.html',
  styleUrls: ['./new-profil.component.scss']
})
export class NewProfilComponent implements OnInit {

  page: Page[];
  seletedPages: any[];
  tabData: any = [{}];
  public nodeData = new TreeViewMetaData();
  isDataLoaded: boolean = false;

  profil: Profil;
  update: boolean = false;
  d: any = [{}];
  panelOpenState = false;
  errorMessage = '';
  error: boolean = false;

  constructor(@Optional() @Inject(MAT_DIALOG_DATA) public data: Profil, private profilService: ProfilService,private _snackBar: MatSnackBar,private pageService: PageService) {
    this.profil = new Profil();
  }

  ngOnInit(): void {
    if (this.data) {
      this.update  = true;
      this.profil = this.data;
      this.nodeData.seletedRow = this.profil.role;
    }

    this.pageService.findAll().subscribe(data => {
      this.page = data.payload;
      this.tabData = data.payload;
      console.log("TabData Content", this.tabData);
      
      this.nodeData.type = 'PAGE';
      this.nodeData.columnToShow = 'intitule'
      this.nodeData.data = data.payload;
      this.nodeData.parentRow = 'father'
      this.nodeData.rowKey = 'codePage'
      this.nodeData.title = 'Liste des page';
      this.isDataLoaded = true;
    });

  }

  receiveMessage($event) {
    this.seletedPages = $event

  }

  onSubmit(): void {
    this.profil.role = "";
    this.seletedPages.forEach(element => {
      this.profil.role = this.profil.role+element.item.codePage + "#";
    });
    if (this.update) {
      // MAJ en BD
      this.profilService.update(this.profil).subscribe(
        result => {
          this.d = result;
          if (this.d.status === 'OK') {
            this.openSnackBar(this.d.message, 'Ok');
            this.reload();
          } else {
            this.openSnackBar(this.d.message, 'Retour');
          }
        },
        error => {
          this.error = true;
          this.errorMessage = 'Impossible de contacter le serveur !';
               }
          );
        } else {
          // Ajout en BD
          console.log(this.profil);
          this.profil.creationDate = new Date();
          this.profil.creatorCode = 'Elisée';
          this.profil.deleted = false;

          this.profilService.save(this.profil).subscribe(
            result => {
              this.d = result;
              if (this.d.status === 'OK') {
                this.openSnackBar(this.d.message, 'Ok');
                this.reload();
              } else {
                this.openSnackBar(this.d.message, 'Retour');
              }
            },
            error => {
              this.error = true;
              this.errorMessage = 'Impossible de contacter le serveur !';
               }
              );
            }
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

        }

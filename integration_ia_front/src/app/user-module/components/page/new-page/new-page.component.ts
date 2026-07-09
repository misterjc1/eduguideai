import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Page } from 'app/user-module/class/Page';
import { Transverse } from 'app/user-module/class/Transverse';
import { PageService } from 'app/user-module/service/page.service';

@Component({
  selector: 'app-new-page',
  templateUrl: './new-page.component.html',
  styleUrls: ['./new-page.component.scss']
})
export class NewPageComponent implements OnInit {

  page: Page;
  pages: Page[];
  update: boolean;

  TypePage: Transverse[] = [
    { value: 'PAGE', viewValue: 'Page' },
    { value: 'SECTION', viewValue: 'Section' },
    { value: 'BUTTON', viewValue: 'Bouton' }
  ];
  
  constructor(public dialog: MatDialog, private pageService: PageService, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.page = new Page();
  }

  ngOnInit() {

    this.pageService.findAll().subscribe(data => {
      this.pages = data.payload;
    });
    this.update = false;
    if (this.data && this.data.page) {
      this.update = true;
      this.page = this.data.page;
    }
    if (this.data && this.data.father) {
      this.page.father = this.data.father;
    }

  }

  onSubmit(): void {
    if (this.update) {
      // MAJ en BD
      this.pageService.update(this.page).subscribe(result => this.goPagesList());
    } else {
      // Ajout en BD
      this.page.creationDate = new Date();
      this.page.creatorCode = 'bill';
      this.pageService.save(this.page).subscribe(result => this.goPagesList());
    }
  }
  goPagesList() {
    location.reload();
  }

}

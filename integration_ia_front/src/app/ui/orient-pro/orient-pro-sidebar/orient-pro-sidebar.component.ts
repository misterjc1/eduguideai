import { Component, OnInit } from '@angular/core';
import { NewOrientProComponent } from '../new-orient-pro/new-orient-pro.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-orient-pro-sidebar',
  templateUrl: './orient-pro-sidebar.component.html',
  styleUrls: ['./orient-pro-sidebar.component.css']
})
export class OrientProSidebarComponent implements OnInit {

  constructor(private dialog: MatDialog) { }
  ngOnInit(): void {
  }

  openNewOrientProModal() {
    this.dialog.open(NewOrientProComponent, {
      width: '500px',  // Largeur du modal
      data: {} // Passer des données si nécessaire
    });
  }

  

}

import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-analyse-pred',
  templateUrl: './analyse-pred.component.html',
  styleUrls: ['./analyse-pred.component.css']
})
export class AnalysePredComponent implements OnInit {

  constructor(@Optional() @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
  }

}

import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Parametre } from 'app/class/Parametre';

@Component({
  selector: 'app-use-parametre',
  templateUrl: './use-parametre.component.html',
  styleUrls: ['./use-parametre.component.css']
})
export class UseParametreComponent implements OnInit {
  @Input() parametres: Parametre[] = [];  // Reçois la liste des paramètres du parent
  @Output() parametreSelected = new EventEmitter<string>();  // Émet l'événement quand un paramètre est sélectionné
  selectedParametre: string;
  constructor() { }

  ngOnInit(): void {
  }

  onParametreChange(event: any) {
    this.selectedParametre = event.target.value;
    this.parametreSelected.emit(this.selectedParametre);  // Émet l'événement au parent

  }

}

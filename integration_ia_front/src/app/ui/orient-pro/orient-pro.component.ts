import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-orient-pro',
  templateUrl: './orient-pro.component.html',
  styleUrls: ['./orient-pro.component.css']
})
export class OrientProComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  // Fonction pour "En discutant d’après suggestions"
  startDiscussion() {
    console.log('Commencer la simulation des efforts');
    // Logique pour rediriger vers une autre page ou un composant spécifique
  }

  // Fonction pour "En choisissant directement"
  startChoice() {
    console.log('Commencer en choisissant directement');
    // Logique pour rediriger vers une autre page ou un composant spécifique
  }

  goToNiveau() {
    this.router.navigate(['/niveau']);
  }

  goToSimulation() {
    this.router.navigate(['/simulation']);
  }

}

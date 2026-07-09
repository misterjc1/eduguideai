import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Cinematique } from 'app/class/Cinematique';
import { Niveau } from 'app/class/Enums/Niveau';
import { CinematiqueService } from 'app/services/cinematique.service';

@Component({
  selector: 'app-deuxieme-niveau',
  templateUrl: './deuxieme-niveau.component.html',
  styleUrls: ['./deuxieme-niveau.component.css']
})
export class DeuxiemeNiveauComponent implements OnInit {

  cinematiquesNiveau2: Cinematique[] = [];
  cinematique: Cinematique[] = [] ;

  constructor(private router: Router,private cinematiqueService: CinematiqueService) { }

  ngOnInit(): void {

    this.loadCinematiquesNiveau2();
  }

  // Récupérer les cinématiques avec le niveau NIVEAU2
  loadCinematiquesNiveau2() {
    this.cinematiqueService.findCinematiquesByNiveau(Niveau.NIVEAU2).subscribe(cinematiques => {
      this.cinematiquesNiveau2 = cinematiques;
    }, error => {
      console.error('Erreur lors de la récupération des cinématiques de niveau 2', error);
    });


  }

  /*loadCinematiqueLogo() {
    this.cinematiqueService.findAll().subscribe({
      next: (data: any) => {
        this.cinematique = data.payload;  // Récupération des services
        
        // Pour chaque service, récupérer son logo s'il en a un
        this.cinematique.forEach((service: any) => {
          if (service.image) {
            // Appel pour récupérer le logo en base64
            this.cinematiqueService.findImage(service.image).subscribe((element: any) => {
              service.imageUrl = "data:image/png;base64," + element.payload;
            }, (err) => {
              console.error(`Erreur lors de la récupération du logo pour le service ${service.nom}`, err);
            });
          }
        });
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des services', err);
      }
    }); 
  }*/



  @Output() cinematiqueSelected = new EventEmitter<string>();  // Émet l'intitulé sélectionné

  // Méthode appelée lorsque l'utilisateur sélectionne une cinématique
  selectCinematique(intitule: string) {
    this.cinematiqueSelected.emit(intitule);  // Émet l'intitulé sélectionné
  }

  // Méthode de redirection
  redirectTo(url: string) {
    this.router.navigate([url]);
  }

}

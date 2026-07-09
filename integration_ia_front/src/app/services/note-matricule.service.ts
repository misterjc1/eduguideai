import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NoteMatriculeService {

  constructor() { }

  private notes: { semestre: string; module: string; libelle: string; valeur: string }[] = [];

  // Méthode pour stocker les notes
  setNotes(notes: { semestre: string; module: string; libelle: string; valeur: string }[]): void {
    this.notes = notes;
  }

  // Méthode pour récupérer toutes les notes
  getNotes(): { semestre: string; module: string; libelle: string; valeur: string }[] {
    return this.notes;
  }
}

import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit, Optional, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatStepper } from '@angular/material/stepper';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import { AI_ORIENTATION } from 'app/helpers/url.constants';
import { NoteMatriculeService } from 'app/services/note-matricule.service';

@Component({
  selector: 'app-new-orient-pro',
  templateUrl: './new-orient-pro.component.html',
  styleUrls: ['./new-orient-pro.component.css']
})
export class NewOrientProComponent implements OnInit {
  @ViewChild('stepper') stepper!: MatStepper;

  profileForm!: FormGroup;
  isLoading = false;
  aiResult = '';
  userName = '';
  error = '';

  interets = [
    { label: 'Technologie & Informatique', icon: 'computer', value: 'Technologie et Informatique' },
    { label: 'Santé & Médecine', icon: 'local_hospital', value: 'Santé et Médecine' },
    { label: 'Éducation & Formation', icon: 'school', value: 'Éducation et Formation' },
    { label: 'Business & Finance', icon: 'business', value: 'Business et Finance' },
    { label: 'Agriculture & Environnement', icon: 'eco', value: 'Agriculture et Environnement' },
    { label: 'Art & Communication', icon: 'brush', value: 'Art et Communication' },
    { label: 'Droit & Administration', icon: 'gavel', value: 'Droit et Administration' },
    { label: 'Ingénierie & BTP', icon: 'engineering', value: 'Ingénierie et BTP' },
  ];

  competences = [
    'Programmation', 'Analyse & Raisonnement', 'Communication', 'Gestion de projets',
    'Leadership', 'Créativité', 'Mathématiques', 'Langues étrangères',
    'Recherche', 'Travail en équipe'
  ];

  objectifs = [
    'Créer ma propre entreprise', 'Travailler dans la fonction publique',
    'Rejoindre une ONG / organisation internationale', 'Travailler dans une grande entreprise privée',
    'Poursuivre des études supérieures / doctorat', 'Travailler à l\'étranger',
    'Contribuer au développement du Burkina Faso'
  ];

  valeurs = [
    'Impact social', 'Stabilité financière', 'Innovation', 'Prestige',
    'Liberté / Autonomie', 'Équilibre vie perso / pro', 'Apprentissage continu'
  ];

  constructor(
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private http: HttpClient,
    private tokenStorage: TokenStorageService,
    private notesService: NoteMatriculeService,
  ) {}

  ngOnInit(): void {
    const user = this.tokenStorage.getUser();
    this.userName = user?.prenom || user?.username || 'Étudiant';

    const notes = this.notesService.getNotes();
    const filiere = notes.length > 0 ? 'Informatique / Sciences' : 'Non précisée';

    this.profileForm = this.fb.group({
      filiere: [filiere, Validators.required],
      interets: [[], [Validators.required, Validators.minLength(1)]],
      competences: [[], [Validators.required, Validators.minLength(1)]],
      objectif: ['', Validators.required],
      valeurs: [[], [Validators.required, Validators.minLength(1)]],
    });
  }

  toggleChip(field: string, value: string): void {
    const ctrl = this.profileForm.get(field);
    if (!ctrl) return;
    const current: string[] = ctrl.value || [];
    const idx = current.indexOf(value);
    if (idx >= 0) current.splice(idx, 1);
    else current.push(value);
    ctrl.setValue([...current]);
  }

  isSelected(field: string, value: string): boolean {
    return (this.profileForm.get(field)?.value || []).includes(value);
  }

  generateRecommendations(): void {
    if (this.profileForm.invalid) return;
    this.isLoading = true;
    this.error = '';
    this.aiResult = '';

    const v = this.profileForm.value;
    const body = {
      filiere: v.filiere,
      interets: v.interets.join(', '),
      competences: v.competences.join(', '),
      objectif: v.objectif,
      valeurs: v.valeurs.join(', '),
    };

    this.http.post<{ recommandations: string }>(AI_ORIENTATION, body).subscribe({
      next: (res) => {
        this.aiResult = res.recommandations;
        this.isLoading = false;
        setTimeout(() => this.stepper?.next(), 100);
      },
      error: () => {
        this.error = 'Impossible de contacter le service IA. Vérifiez votre connexion et réessayez.';
        this.isLoading = false;
      }
    });
  }

  formatResult(text: string): string {
    return text
      .replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')
      .replace(/##\s*(.*)/g, '<h4>$1</h4>')
      .replace(/\n\n/g, '</p><p>')
      .replace(/\n/g, '<br>');
  }

  restart(): void {
    this.aiResult = '';
    this.error = '';
    this.profileForm.reset({ filiere: this.profileForm.get('filiere')?.value, interets: [], competences: [], valeurs: [] });
    this.stepper?.reset();
  }
}

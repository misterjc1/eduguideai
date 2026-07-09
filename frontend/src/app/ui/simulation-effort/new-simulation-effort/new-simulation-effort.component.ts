import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit, Optional, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatStepper } from '@angular/material/stepper';
import { NoteMatriculeService } from 'app/services/note-matricule.service';
import { ParametreService } from 'app/services/parametre.service';
import { InscritService } from 'app/services/inscrit.service';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import { FIND_NOTES_BY_MATRICULE, AI_SIMULATION } from 'app/helpers/url.constants';

interface Note { semestre: string; module: string; libelle: string; valeur: string; }

@Component({
  selector: 'app-new-simulation-effort',
  templateUrl: './new-simulation-effort.component.html',
  styleUrls: ['./new-simulation-effort.component.css']
})
export class NewSimulationEffortComponent implements OnInit {
  @ViewChild('stepper') stepper!: MatStepper;

  form!: FormGroup;
  notes: Note[] = [];
  niveaux: any[] = [];
  userName = '';
  isLoadingNotes = false;
  isLoadingAI = false;
  aiResult = '';
  error = '';

  isAdmin = false;
  inscrits: any[] = [];
  selectedInscrit: any = null;
  isLoadingInscrits = false;

  objectifs = [
    { label: 'Améliorer mes notes', icon: 'trending_up', value: 'Améliorer mes notes' },
    { label: 'Gérer mon temps', icon: 'schedule', value: 'Gérer mon temps' },
    { label: 'Préparer mes examens', icon: 'event_note', value: 'Préparer mes examens' },
    { label: 'Obtenir une bourse', icon: 'military_tech', value: 'Obtenir une bourse scolaire' },
    { label: 'Major de promotion', icon: 'emoji_events', value: 'Être major de promotion' },
    { label: 'Avoir la moyenne', icon: 'check_circle', value: 'Avoir la moyenne dans tous les modules' },
    { label: 'Meilleure mention', icon: 'star', value: 'Obtenir la meilleure mention' },
    { label: 'Excellence académique', icon: 'workspace_premium', value: 'Être excellent dans mon domaine' },
  ];

  intensites = [
    { label: 'Légère (1-2h/jour)', value: '1 à 2 heures par jour', color: '#22C55E' },
    { label: 'Modérée (3-4h/jour)', value: '3 à 4 heures par jour', color: '#F59E0B' },
    { label: 'Intensive (5-6h/jour)', value: '5 à 6 heures par jour', color: '#EF4444' },
  ];

  constructor(
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private parametreService: ParametreService,
    private notesService: NoteMatriculeService,
    private inscritService: InscritService,
    private http: HttpClient,
    private tokenStorage: TokenStorageService,
  ) {}

  ngOnInit(): void {
    const user = this.tokenStorage.getUser();
    this.userName = user?.prenom || user?.username || 'Étudiant';
    this.isAdmin = user?.type === 'AGENT';

    this.form = this.fb.group({
      objectifs: [[], [Validators.required, Validators.minLength(1)]],
      intensite: ['3 à 4 heures par jour', Validators.required],
      duree: ['4 semaines', Validators.required],
    });

    if (this.isAdmin) {
      this.loadInscrits();
    } else {
      this.loadNotes();
    }
    this.loadNiveaux();
  }

  loadInscrits(): void {
    this.isLoadingInscrits = true;
    this.inscritService.findAll().subscribe({
      next: (r: any) => {
        this.inscrits = r?.payload || [];
        this.isLoadingInscrits = false;
      },
      error: () => { this.isLoadingInscrits = false; }
    });
  }

  onInscritChange(inscrit: any): void {
    this.selectedInscrit = inscrit;
    this.notes = [];
    if (!inscrit) return;
    this.userName = inscrit.prenom || inscrit.nom || inscrit.matricule;
    this.loadNotesByMatricule(inscrit.matricule);
  }

  loadNiveaux(): void {
    this.parametreService.findAll().subscribe({
      next: (r: any) => {
        const payload = r?.payload || r;
        if (Array.isArray(payload)) this.niveaux = payload.filter((n: any) => !n.isDeleted);
      },
      error: () => {}
    });
  }

  loadNotes(): void {
    const cached = this.notesService.getNotes();
    if (cached && cached.length > 0) {
      this.notes = cached.filter(n => n.valeur && n.valeur !== 'null');
      return;
    }
    const username = this.tokenStorage.getUser()?.username || '';
    this.loadNotesByMatricule(username);
  }

  private loadNotesByMatricule(matricule: string): void {
    this.isLoadingNotes = true;
    this.http.get<any>(`${FIND_NOTES_BY_MATRICULE}${matricule}`).subscribe({
      next: (r: any) => {
        const payload = r?.payload || [];
        this.notes = payload.map((n: any) => ({
          semestre: n.semestre || '', module: n.module || '',
          libelle: n.libelle || '', valeur: String(n.valeur ?? ''),
        })).filter((n: any) => n.valeur !== '' && n.valeur !== 'null');
        if (!this.isAdmin) this.notesService.setNotes(this.notes);
        this.isLoadingNotes = false;
      },
      error: () => { this.isLoadingNotes = false; }
    });
  }

  get moyenneNotes(): number {
    const vals = this.notes.map(n => parseFloat(n.valeur)).filter(v => !isNaN(v));
    if (vals.length === 0) return 0;
    return vals.reduce((a, b) => a + b, 0) / vals.length;
  }

  toggleObjectif(value: string): void {
    const ctrl = this.form.get('objectifs')!;
    const current: string[] = ctrl.value || [];
    const idx = current.indexOf(value);
    if (idx >= 0) current.splice(idx, 1); else current.push(value);
    ctrl.setValue([...current]);
  }

  isObjectifSelected(value: string): boolean {
    return (this.form.get('objectifs')?.value || []).includes(value);
  }

  durees = ['2 semaines', '4 semaines', '2 mois', 'Un semestre'];

  generateSimulation(): void {
    if (this.form.invalid || this.notes.length === 0) return;
    this.isLoadingAI = true;
    this.error = '';
    this.aiResult = '';

    const v = this.form.value;
    const notesResume = this.notes.map(n => `${n.libelle || n.module}: ${n.valeur}/20`).join(', ');
    const body = {
      username: this.userName,
      niveau: this.niveaux[0]?.libelle || 'Non précisé',
      objectifs: v.objectifs.join(', '),
      notesResume,
      heuresDisponibles: `${v.intensite} pendant ${v.duree}`,
    };

    this.http.post<{ plan: string }>(AI_SIMULATION, body).subscribe({
      next: (r) => {
        this.aiResult = r.plan;
        this.isLoadingAI = false;
        setTimeout(() => this.stepper?.next(), 100);
      },
      error: () => {
        this.error = 'Impossible de contacter le service IA. Vérifiez votre connexion.';
        this.isLoadingAI = false;
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
    this.aiResult = ''; this.error = '';
    this.form.reset({ objectifs: [], intensite: '3 à 4 heures par jour', duree: '4 semaines' });
    this.stepper?.reset();
  }
}

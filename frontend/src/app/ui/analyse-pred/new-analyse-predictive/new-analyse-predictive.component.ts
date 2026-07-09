import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { InscritService } from 'app/services/inscrit.service';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import { FIND_NOTES_BY_MATRICULE, AI_ANALYSE } from 'app/helpers/url.constants';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

interface Note { semestre: string; module: string; libelle: string; valeur: string; }

@Component({
  selector: 'app-new-analyse-predictive',
  templateUrl: './new-analyse-predictive.component.html',
  styleUrls: ['./new-analyse-predictive.component.css']
})
export class NewAnalysePredictiveComponent implements OnInit {

  form!: FormGroup;

  // State
  isAdmin = false;
  currentUser: any = null;

  // Inscrit loading
  inscrits: any[] = [];
  selectedInscrit: any = null;
  isLoadingInscrits = false;
  needInscritSelection = false;  // true quand on ne peut pas auto-détecter

  // Notes
  notes: Note[] = [];
  isLoadingNotes = false;

  // Results
  showResults = false;
  moyenneActuelle = 0;
  moyennePredite = 0;
  recapTable: any[] = [];
  selectedObjectifs: string[] = [];

  // AI
  aiAnalyse = '';
  isLoadingAI = false;

  objectifs = [
    { label: 'Mention Passable (≥10)',       icon: 'check_circle_outline' },
    { label: 'Mention Assez Bien (≥12)',      icon: 'grade' },
    { label: 'Mention Bien (≥14)',            icon: 'star_half' },
    { label: 'Mention Très Bien (≥16)',       icon: 'star' },
    { label: 'Être Major de promotion',       icon: 'emoji_events' },
    { label: 'Réussir tous les modules',      icon: 'task_alt' },
  ];

  constructor(
    private fb: FormBuilder,
    private inscritService: InscritService,
    private http: HttpClient,
    private tokenStorage: TokenStorageService,
  ) {}

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    this.isAdmin = this.currentUser?.type === 'AGENT';

    this.form = this.fb.group({
      objectifs: [[], (control: any) => (control.value?.length > 0 ? null : { required: true })],
    });

    this.loadInscritsAndResolve();
  }

  // Charge tous les inscrits, puis tente une résolution automatique pour les étudiants
  private loadInscritsAndResolve(): void {
    this.isLoadingInscrits = true;
    this.inscritService.findAll().pipe(
      catchError(() => of({ payload: [] } as any))
    ).subscribe((r: any) => {
      this.inscrits = r?.payload || [];
      this.isLoadingInscrits = false;

      if (this.isAdmin) {
        // Admin : doit choisir lui-même
        this.needInscritSelection = true;
      } else {
        // Étudiant : essaie de trouver automatiquement son inscrit
        this.tryAutoMatch();
      }
    });
  }

  private tryAutoMatch(): void {
    const user = this.currentUser;
    if (!user || !this.inscrits.length) {
      this.needInscritSelection = true;
      return;
    }

    // Tentatives de correspondance par ordre de fiabilité
    let match = this.inscrits.find(i =>
      i.email && user.email && i.email.toLowerCase() === user.email.toLowerCase()
    );
    if (!match) {
      match = this.inscrits.find(i => i.matricule === user.username);
    }
    if (!match && user.nom && user.prenom) {
      match = this.inscrits.find(i =>
        i.nom?.toLowerCase() === user.nom?.toLowerCase() &&
        i.prenom?.toLowerCase() === user.prenom?.toLowerCase()
      );
    }

    if (match) {
      this.selectedInscrit = match;
      this.loadNotesByMatricule(match.matricule);
    } else {
      // Pas de correspondance : on laisse l'étudiant se sélectionner
      this.needInscritSelection = true;
    }
  }

  onInscritChange(inscrit: any): void {
    this.selectedInscrit = inscrit;
    this.notes = [];
    this.showResults = false;
    if (!inscrit) return;
    this.loadNotesByMatricule(inscrit.matricule);
  }

  private loadNotesByMatricule(matricule: string): void {
    this.isLoadingNotes = true;
    this.notes = [];
    this.http.get<any>(`${FIND_NOTES_BY_MATRICULE}${matricule}`).pipe(
      catchError(() => of({ payload: [] }))
    ).subscribe((r: any) => {
      const payload: any[] = r?.payload || [];
      this.notes = payload
        .map((n: any) => ({
          semestre: n.semestre || '',
          module: n.module || '',
          libelle: n.libelle || n.module || '',
          valeur: String(n.valeur ?? ''),
        }))
        .filter(n => n.valeur !== '' && n.valeur !== 'null' && !isNaN(parseFloat(n.valeur)));
      this.isLoadingNotes = false;
    });
  }

  get moyenneNotes(): number {
    const vals = this.notes.map(n => parseFloat(n.valeur)).filter(v => !isNaN(v));
    if (!vals.length) return 0;
    return vals.reduce((a, b) => a + b, 0) / vals.length;
  }

  getNoteColor(val: number): string {
    if (val >= 14) return '#22C55E';
    if (val >= 10) return '#F59E0B';
    return '#EF4444';
  }

  toggleObjectif(label: string): void {
    const current: string[] = this.form.get('objectifs')!.value || [];
    const idx = current.indexOf(label);
    const updated = idx >= 0 ? current.filter(v => v !== label) : [...current, label];
    this.form.get('objectifs')!.setValue(updated);
    this.form.get('objectifs')!.updateValueAndValidity();
  }

  isObjectifSelected(label: string): boolean {
    return (this.form.get('objectifs')?.value || []).includes(label);
  }

  runAnalysis(): void {
    if (!this.form.valid || !this.notes.length) return;

    this.selectedObjectifs = this.form.get('objectifs')!.value;
    const vals = this.notes.map(n => parseFloat(n.valeur)).filter(v => !isNaN(v));

    this.moyenneActuelle = vals.reduce((a, b) => a + b, 0) / vals.length;
    this.moyennePredite = this.predictLinear(vals);

    this.recapTable = this.notes
      .filter(n => !isNaN(parseFloat(n.valeur)))
      .map(n => ({
        libelle: n.libelle || n.module,
        semestre: n.semestre,
        valeur: parseFloat(n.valeur),
        status: parseFloat(n.valeur) >= 10 ? 'ok' : 'fail',
      }));

    this.showResults = true;
    this.callAI();

    // Scroll vers les résultats
    setTimeout(() => {
      const el = document.querySelector('.ap-results-section');
      if (el) el.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }, 100);
  }

  private predictLinear(vals: number[]): number {
    if (!vals.length) return 10;
    const n = vals.length;
    const meanX = (n - 1) / 2;
    const meanY = vals.reduce((a, b) => a + b, 0) / n;
    let num = 0, den = 0;
    vals.forEach((y, x) => {
      num += (x - meanX) * (y - meanY);
      den += (x - meanX) ** 2;
    });
    const slope = den !== 0 ? num / den : 0;
    const intercept = meanY - slope * meanX;
    const next = slope * n + intercept;
    return Math.max(0, Math.min(20, Math.round(((next + meanY) / 2) * 100) / 100));
  }

  private callAI(): void {
    this.isLoadingAI = true;
    this.aiAnalyse = '';
    const nomEtudiant = this.selectedInscrit
      ? `${this.selectedInscrit.prenom || ''} ${this.selectedInscrit.nom || ''}`.trim()
      : (this.currentUser?.prenom || this.currentUser?.username || 'Étudiant');
    const notesResume = this.notes
      .map(n => `${n.libelle || n.module}: ${n.valeur}/20`)
      .join(', ');
    const body = {
      username: nomEtudiant,
      niveau: this.selectedInscrit?.niveau?.libelle || 'Non précisé',
      objectif: this.selectedObjectifs.join(', '),
      moyenneActuelle: this.moyenneActuelle,
      moyennePredite: this.moyennePredite,
      notesResume,
    };
    this.http.post<{ analyse: string }>(AI_ANALYSE, body).pipe(
      catchError(() => of({ analyse: '' }))
    ).subscribe(r => {
      this.aiAnalyse = r?.analyse || '';
      this.isLoadingAI = false;
    });
  }

  formatAI(text: string): string {
    return text
      .replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')
      .replace(/##\s*(.*)/g, '<h4 style="color:#1565C0;margin:12px 0 6px">$1</h4>')
      .replace(/\n/g, '<br>');
  }

  restart(): void {
    this.showResults = false;
    this.aiAnalyse = '';
    this.moyenneActuelle = 0;
    this.moyennePredite = 0;
    this.recapTable = [];
    this.selectedObjectifs = [];
    this.form.reset({ objectifs: [] });
  }
}

import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { MatTableDataSource } from '@angular/material/table';
import { NoteService } from 'app/services/note.service';
import { NiveauService } from 'app/services/niveau.service';
import { InscritService } from 'app/services/inscrit.service';
import { AI_FRAUD_ANALYSIS } from 'app/helpers/url.constants';

interface NoteRow {
  inscrit: any;
  module: string;
  libelle: string;
  valeur: string;
  semestre: string;
  selected: FormControl;
  [key: string]: any;
}

interface FraudResult {
  inscrit: any;
  module: string;
  libelle: string;
  valeur: string;
  semestre: string;
  rule: string;
  ruleCode: number;
}

@Component({
  selector: 'app-new-detection-fraud',
  templateUrl: './new-detection-fraud.component.html',
  styleUrls: ['./new-detection-fraud.component.css']
})
export class NewDetectionFraudComponent implements OnInit {
  @ViewChild('stepper') stepper!: MatStepper;

  firstFormGroup!: FormGroup;
  secondFormGroup!: FormGroup;

  niveaux: any[] = [];
  selectedNiveau: any = null;
  isLoadingNiveaux = false;
  isLoadingNotes = false;
  isLoadingAI = false;

  notesDataSource: MatTableDataSource<NoteRow> | undefined;
  fraudResults: FraudResult[] = [];
  aiNarrative = '';

  displayedColumns = ['select', 'inscrit', 'module', 'valeur', 'semestre'];
  displayedColumnsFraud = ['inscrit', 'module', 'valeur', 'semestre', 'rule'];

  rules = [
    { key: 'fraudRule1', label: 'Notes identiques répétées', desc: '≥ 3 notes exactement identiques entre étudiants différents', icon: 'content_copy', code: 1 },
    { key: 'fraudRule2', label: 'Proximité suspecte', desc: 'Écart de ±0.5 point entre plusieurs étudiants sur le même module', icon: 'compare_arrows', code: 2 },
    { key: 'fraudRule3', label: 'Patron de progression similaire', desc: 'Même tendance hausse/baisse sur plusieurs semestres', icon: 'show_chart', code: 3 },
  ];

  constructor(
    private fb: FormBuilder,
    private noteService: NoteService,
    private niveauService: NiveauService,
    private inscritService: InscritService,
    private http: HttpClient,
  ) {}

  ngOnInit(): void {
    this.firstFormGroup = this.fb.group({ selectAll: [false] });
    this.secondFormGroup = this.fb.group({ fraudRule1: [true], fraudRule2: [false], fraudRule3: [false] });
    this.loadNiveaux();
  }

  loadNiveaux(): void {
    this.isLoadingNiveaux = true;
    this.niveauService.findAll().subscribe({
      next: (res: any) => {
        this.niveaux = res?.payload || [];
        this.isLoadingNiveaux = false;
      },
      error: () => { this.isLoadingNiveaux = false; }
    });
  }

  onNiveauChange(niveau: any): void {
    this.selectedNiveau = niveau;
    this.notesDataSource = undefined;
    this.firstFormGroup.get('selectAll')?.setValue(false);
    if (!niveau) return;
    this.loadNotesForNiveau(niveau);
  }

  private loadNotesForNiveau(niveau: any): void {
    this.isLoadingNotes = true;
    this.inscritService.findAll().subscribe({
      next: (res: any) => {
        const allInscrits: any[] = res?.payload || [];
        const filtered = allInscrits.filter(i =>
          i.niveau?.codeNiveau === niveau.codeNiveau || i.niveau?.idNiveau === niveau.idNiveau
        );

        if (filtered.length === 0) {
          this.notesDataSource = new MatTableDataSource<NoteRow>([]);
          this.isLoadingNotes = false;
          return;
        }

        this.noteService.getAllNotesByInscrits(filtered).subscribe({
          next: (notes: any[]) => {
            const rows: NoteRow[] = notes.map((n: any) => ({ ...n, selected: new FormControl(false) }));
            this.notesDataSource = new MatTableDataSource<NoteRow>(rows);
            this.isLoadingNotes = false;
          },
          error: () => { this.isLoadingNotes = false; }
        });
      },
      error: () => { this.isLoadingNotes = false; }
    });
  }

  get selectedCount(): number {
    return this.notesDataSource?.data.filter(n => n.selected.value).length || 0;
  }

  toggleAllNotes(): void {
    if (!this.notesDataSource) return;
    const val = this.firstFormGroup.get('selectAll')?.value;
    this.notesDataSource.data.forEach(n => n.selected.setValue(val));
  }

  runDetection(): void {
    const selectedNotes = this.notesDataSource?.data.filter(n => n.selected.value) || [];
    this.fraudResults = [];

    if (this.secondFormGroup.get('fraudRule1')?.value) this.applyIdenticalRule(selectedNotes);
    if (this.secondFormGroup.get('fraudRule2')?.value) this.applyProximityRule(selectedNotes);
    if (this.secondFormGroup.get('fraudRule3')?.value) this.applyPatternRule(selectedNotes);

    this.stepper?.next();
    if (this.fraudResults.length > 0) this.callAI(selectedNotes);
  }

  private applyIdenticalRule(notes: NoteRow[]): void {
    const counts = new Map<string, NoteRow[]>();
    notes.forEach(n => {
      const key = `${n.module || n.libelle}|${n.valeur}`;
      if (!counts.has(key)) counts.set(key, []);
      counts.get(key)!.push(n);
    });
    counts.forEach((list) => {
      if (list.length >= 3) {
        list.forEach(n => this.fraudResults.push({ ...n, rule: 'Notes identiques répétées (≥ 3)', ruleCode: 1 }));
      }
    });
  }

  private applyProximityRule(notes: NoteRow[]): void {
    for (let i = 0; i < notes.length; i++) {
      for (let j = i + 1; j < notes.length; j++) {
        const modI = notes[i].module || notes[i].libelle;
        const modJ = notes[j].module || notes[j].libelle;
        if (modI !== modJ) continue;
        const inscritI = notes[i].inscrit?.matricule;
        const inscritJ = notes[j].inscrit?.matricule;
        if (inscritI === inscritJ) continue;
        const diff = Math.abs(Number(notes[i].valeur) - Number(notes[j].valeur));
        if (diff <= 0.5 && diff > 0) {
          this.fraudResults.push({ ...notes[i], rule: 'Proximité suspecte (±0.5)', ruleCode: 2 });
          this.fraudResults.push({ ...notes[j], rule: 'Proximité suspecte (±0.5)', ruleCode: 2 });
        }
      }
    }
  }

  private applyPatternRule(notes: NoteRow[]): void {
    const groups: { [mat: string]: NoteRow[] } = {};
    notes.forEach(n => {
      const mat = n.inscrit?.matricule || 'unknown';
      if (!groups[mat]) groups[mat] = [];
      groups[mat].push(n);
    });
    const patterns: string[] = [];
    for (const mat in groups) {
      const sn = groups[mat];
      if (sn.length < 2) continue;
      const pattern = sn.map((n, i) => i === 0 ? '=' : Number(n.valeur) > Number(sn[i-1].valeur) ? '+' : '-').join('');
      patterns.push(pattern);
    }
    const patternCounts = new Map<string, string[]>();
    Object.keys(groups).forEach((mat, idx) => {
      const p = patterns[idx];
      if (!patternCounts.has(p)) patternCounts.set(p, []);
      patternCounts.get(p)!.push(mat);
    });
    patternCounts.forEach((mats, pattern) => {
      if (mats.length >= 2 && pattern.length > 1) {
        mats.forEach(mat => {
          groups[mat].forEach(n => {
            this.fraudResults.push({ ...n, rule: 'Patron de progression similaire', ruleCode: 3 });
          });
        });
      }
    });
  }

  private callAI(allNotes: NoteRow[]): void {
    this.isLoadingAI = true;
    const rulesApplied = this.rules
      .filter(r => this.secondFormGroup.get(r.key)?.value)
      .map(r => r.label).join(', ');
    const suspectSample = this.fraudResults.slice(0, 5)
      .map(r => `${r.inscrit?.matricule || '-'} — ${r.libelle || r.module} (${r.valeur}/20) — Règle: ${r.rule}`)
      .join('\n');

    const body = {
      totalNotes: allNotes.length,
      anomalies: this.fraudResults.length,
      regles: rulesApplied,
      resume: suspectSample
    };
    this.http.post<{ analyse: string }>(AI_FRAUD_ANALYSIS, body).subscribe({
      next: (r) => { this.aiNarrative = r.analyse; this.isLoadingAI = false; },
      error: () => { this.isLoadingAI = false; }
    });
  }

  formatAI(text: string): string {
    return text
      .replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')
      .replace(/##\s*(.*)/g, '<h4>$1</h4>')
      .replace(/\n/g, '<br>');
  }

  getRuleColor(code: number): string {
    if (code === 1) return '#DC2626';
    if (code === 2) return '#F59E0B';
    return '#8B5CF6';
  }

  restart(): void {
    this.fraudResults = [];
    this.aiNarrative = '';
    this.selectedNiveau = null;
    this.notesDataSource = undefined;
    this.firstFormGroup.reset({ selectAll: false });
    this.secondFormGroup.reset({ fraudRule1: true, fraudRule2: false, fraudRule3: false });
    this.stepper?.reset();
  }
}

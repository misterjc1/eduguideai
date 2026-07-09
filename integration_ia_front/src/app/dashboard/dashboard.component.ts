import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TokenStorageService } from './../user-module/service/token-storage.service';
import { ServiceService } from 'app/services/service.service';
import { MatDialog } from '@angular/material/dialog';
import { NewOrientProComponent } from 'app/ui/orient-pro/new-orient-pro/new-orient-pro.component';
import { NewAnalysePredictiveComponent } from 'app/ui/analyse-pred/new-analyse-predictive/new-analyse-predictive.component';
import { NewSimulationEffortComponent } from 'app/ui/simulation-effort/new-simulation-effort/new-simulation-effort.component';
import { NewAssistantVirtuelComponent } from 'app/ui/assistant-v/new-assistant-virtuel/new-assistant-virtuel.component';
import { NewDetectionFraudComponent } from 'app/ui/detection-fraud/new-detection-fraud/new-detection-fraud.component';
import { DASHBOARD_STATS } from 'app/helpers/url.constants';

const DEMO_SERVICES: any[] = [
  {
    typeService: 'ORIENTATION_PROFFESSIONNELLE',
    message: 'Découvrez le parcours professionnel qui correspond à votre profil grâce à l\'IA.',
    icon: 'explore'
  },
  {
    typeService: 'ANALYSE_PREDICTIVE',
    message: 'Anticipez vos résultats académiques et identifiez les matières à renforcer.',
    icon: 'insights'
  },
  {
    typeService: 'SIMULATION_EFFORTS',
    message: 'Planifiez vos efforts académiques pour atteindre vos objectifs de réussite.',
    icon: 'trending_up'
  },
  {
    typeService: 'ASSISTANT_VIRTUEL',
    message: 'Posez vos questions académiques et obtenez des réponses personnalisées par l\'IA.',
    icon: 'smart_toy'
  },
];

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  userConnected: any = {};
  userName = 'Utilisateur';
  userRole = 'Utilisateur';
  userType = '';
  services: any[] = [];
  stats: any = {};
  isLoading = true;

  constructor(
    private tokenService: TokenStorageService,
    private serviceService: ServiceService,
    private http: HttpClient,
    private dialog: MatDialog,
  ) {}

  ngOnInit() {
    this.userConnected = this.tokenService.getUser();
    this.userName = this.userConnected?.prenom
                 || this.userConnected?.nom
                 || this.userConnected?.username
                 || 'Utilisateur';
    this.userType = this.userConnected?.type || '';
    this.userRole = this.getRoleLabel(this.userType);
    this.loadServices();
    this.loadStats();
  }

  getRoleLabel(type: string): string {
    switch (type) {
      case 'AGENT':   return 'Administrateur';
      case 'INSCRIT': return 'Étudiant(e)';
      case 'TUTEUR':  return 'Tuteur / Parent';
      default:        return 'Utilisateur';
    }
  }

  getRoleIcon(type: string): string {
    switch (type) {
      case 'AGENT':   return 'admin_panel_settings';
      case 'INSCRIT': return 'school';
      case 'TUTEUR':  return 'family_restroom';
      default:        return 'person';
    }
  }

  getServiceColor(type: string): string {
    const colors: { [key: string]: string } = {
      'ORIENTATION_PROFFESSIONNELLE': '#1565C0',
      'ANALYSE_PREDICTIVE':          '#E65100',
      'SIMULATION_EFFORTS':          '#2E7D32',
      'ASSISTANT_VIRTUEL':           '#6A1B9A',
      'DETECTION_FRAUDE':            '#C62828',
    };
    return colors[type] || '#455A64';
  }

  getServiceLabel(type: string): string {
    const labels: { [key: string]: string } = {
      'ORIENTATION_PROFFESSIONNELLE': 'Orientation Professionnelle',
      'ANALYSE_PREDICTIVE':          'Analyse Prédictive des Notes',
      'SIMULATION_EFFORTS':          'Simulation des Efforts',
      'ASSISTANT_VIRTUEL':           'Assistant Virtuel IA',
      'DETECTION_FRAUDE':            'Détection de Fraudes',
    };
    return labels[type] || type;
  }

  getIconForType(type: string): string {
    const icons: { [key: string]: string } = {
      'ORIENTATION_PROFFESSIONNELLE': 'explore',
      'ANALYSE_PREDICTIVE':          'insights',
      'SIMULATION_EFFORTS':          'trending_up',
      'ASSISTANT_VIRTUEL':           'smart_toy',
      'DETECTION_FRAUDE':            'security',
    };
    return icons[type] || 'star';
  }

  get statCards(): any[] {
    if (this.userType === 'INSCRIT') {
      return [
        { icon: 'assignment',   label: 'Mes notes',        value: this.stats.nbNotesPerso || 0,             color: '#1565C0', sub: 'modules évalués' },
        { icon: 'star',         label: 'Moyenne générale', value: (this.stats.moyennePerso || 0) + ' /20',  color: '#E65100', sub: 'tous semestres' },
        { icon: 'emoji_events', label: 'Meilleure note',   value: (this.stats.bestNote || 0) + ' /20',      color: '#2E7D32', sub: 'votre record' },
        { icon: 'smart_toy',    label: 'Services IA',      value: this.stats.nbServices || 4,               color: '#6A1B9A', sub: 'disponibles' },
      ];
    }
    if (this.userType === 'TUTEUR') {
      return [
        { icon: 'people',      label: 'Étudiants liés',   value: 1,                              color: '#1565C0', sub: 'sous votre tutelle' },
        { icon: 'assignment',  label: 'Notes totales',    value: this.stats.nbNotes  || 0,       color: '#E65100', sub: 'enregistrées' },
        { icon: 'folder',      label: 'Niveaux / Filières', value: this.stats.nbNiveaux || 0,    color: '#2E7D32', sub: 'actifs' },
        { icon: 'smart_toy',   label: 'Services IA',      value: this.stats.nbServices || 4,     color: '#6A1B9A', sub: 'disponibles' },
      ];
    }
    return [
      { icon: 'people',      label: 'Étudiants inscrits',  value: this.stats.nbInscrits  || 0, color: '#1565C0', sub: 'actifs' },
      { icon: 'folder',      label: 'Niveaux / Filières',  value: this.stats.nbNiveaux   || 0, color: '#E65100', sub: 'actifs' },
      { icon: 'assignment',  label: 'Notes enregistrées',  value: this.stats.nbNotes     || 0, color: '#2E7D32', sub: 'au total' },
      { icon: 'smart_toy',   label: 'Services IA',         value: this.stats.nbServices  || 0, color: '#6A1B9A', sub: 'actifs' },
    ];
  }

  loadStats() {
    const username = this.userConnected?.username || '';
    this.http.get<any>(`${DASHBOARD_STATS}?username=${username}`).subscribe({
      next: (data) => { this.stats = data; this.isLoading = false; },
      error: ()     => { this.isLoading = false; }
    });
  }

  loadServices() {
    this.serviceService.findAll().subscribe({
      next: (data: any) => {
        const payload: any[] = data.payload || [];
        const filtered = payload.filter((s: any) => s.typeService !== 'DETECTION_FRAUDE');
        if (filtered.length === 0) { this.services = DEMO_SERVICES; return; }
        this.services = filtered.map((s: any) => ({
          ...s,
          icon: this.getIconForType(s.typeService),
        }));
      },
      error: () => { this.services = DEMO_SERVICES; }
    });
  }

  navigateToService(serviceType: string) {
    switch (serviceType) {
      case 'ORIENTATION_PROFFESSIONNELLE': this.openModal(NewOrientProComponent,        'Orientation Professionnelle'); break;
      case 'ANALYSE_PREDICTIVE':           this.openModal(NewAnalysePredictiveComponent, 'Analyse Prédictive');         break;
      case 'SIMULATION_EFFORTS':           this.openModal(NewSimulationEffortComponent,  'Simulation des Efforts');     break;
      case 'ASSISTANT_VIRTUEL':            this.openModal(NewAssistantVirtuelComponent,  'Assistant Virtuel');          break;
      case 'DETECTION_FRAUDE':             this.openModal(NewDetectionFraudComponent,    'Détection de Fraudes');       break;
    }
  }

  openModal(component: any, title: string) {
    this.dialog.open(component, { width: '1300px', height: '700px', data: { title } });
  }
}

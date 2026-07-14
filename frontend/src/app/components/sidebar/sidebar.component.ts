import { Component, OnInit } from '@angular/core';
import { PageService } from 'app/user-module/service/page.service';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';

declare const $: any;
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'Dashboard', icon: 'dashboard', class: '' },
    { path: '/utilisateur', title: 'Utilisateurs', icon: 'people', class: '' },
    { path: '/liaison', title: 'Liaison_Inscrits', icon: 'attachment', class: '' },
    { path: '/inscrit', title: 'Inscrit', icon: 'school', class: '' },
    { path: '/niveauinscrit', title: 'Niveau', icon: 'folder', class: '' },
    { path: '/templatePrompt', title: 'Template_Prompt', icon: 'subject', class: '' },
    { path: '/service', title: 'Noyau_Global', icon: 'stars', class: '' },
    { path: '/cinematique', title: 'Cinematique', icon: 'label', class: '' },
    { path: '/typeCinematique', title: 'Type_Cinematique', icon: 'label', class: '' },
    { path: '/orientation', title: 'Orientation_Professionnel', icon: 'home', class: '' },
    { path: '/detection', title: 'Detection_De_Fraudes', icon: 'home', class: '' },
    { path: '/authorisation', title: 'Autorisation', icon: 'people', class: '' },
];

const ADMIN_ROUTES: RouteInfo[] = [
    { path: '/dashboard',     title: 'Dashboard',           icon: 'dashboard',        class: '' },
    { path: '/utilisateur',   title: 'Utilisateurs',        icon: 'people',           class: '' },
    { path: '/liaison',       title: 'Liaisons',            icon: 'link',             class: '' },
    { path: '/inscrit',       title: 'Étudiants',           icon: 'school',           class: '' },
    { path: '/niveauinscrit', title: 'Niveaux',             icon: 'folder',           class: '' },
    { path: '/assistant',     title: 'Assistant Virtuel',   icon: 'smart_toy',        class: '' },
    { path: '/orientation',   title: 'Orientation Pro.',    icon: 'explore',          class: '' },
    { path: '/analyse',       title: 'Analyse Prédictive',  icon: 'insights',         class: '' },
    { path: '/simulation',    title: 'Simulation Efforts',  icon: 'fitness_center',   class: '' },
    { path: '/detection',     title: 'Détect. Fraudes',     icon: 'security',         class: '' },
    { path: '/authorisation', title: 'Autorisations',       icon: 'manage_accounts',  class: '' },
];

const STUDENT_ROUTES: RouteInfo[] = [
    { path: '/dashboard',   title: 'Accueil',              icon: 'home',           class: '' },
    { path: '/assistant',   title: 'Assistant Virtuel',    icon: 'smart_toy',      class: '' },
    { path: '/orientation', title: 'Orientation Pro.',     icon: 'explore',        class: '' },
    { path: '/analyse',     title: 'Analyse Prédictive',   icon: 'insights',       class: '' },
    { path: '/simulation',  title: 'Simulation Efforts',   icon: 'fitness_center', class: '' },
];

const TUTOR_ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'Dashboard',       icon: 'dashboard',  class: '' },
    { path: '/inscrit',   title: 'Mes étudiants',   icon: 'school',     class: '' },
    { path: '/liaison',   title: 'Liaisons',        icon: 'link',       class: '' },
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[] = [];
  utilisateur: any = [{}];
  chequePageView: boolean = false;
  userConnected: any;
  public pages: any = [];
  tabdata: any = [];

  constructor(private tockenStorage: TokenStorageService, private pageService: PageService) { }

  ngOnInit() {
    this.userConnected = this.tockenStorage.getUser();
    const type: string = this.userConnected?.type || '';

    if (type === 'AGENT') {
      this.menuItems = ADMIN_ROUTES;
    } else if (type === 'TUTEUR') {
      this.menuItems = TUTOR_ROUTES;
    } else {
      this.menuItems = STUDENT_ROUTES;
    }
  }

  getInitials(): string {
    const prenom: string = this.userConnected?.prenom || '';
    const nom: string    = this.userConnected?.nom    || '';
    if (prenom && nom) return (prenom[0] + nom[0]).toUpperCase();
    const username: string = this.userConnected?.username || 'U';
    return username[0].toUpperCase();
  }

  getRoleLabel(type: string): string {
    switch (type) {
      case 'AGENT':   return 'Administrateur';
      case 'INSCRIT': return 'Étudiant(e)';
      case 'TUTEUR':  return 'Tuteur / Parent';
      default:        return 'Utilisateur';
    }
  }

  /** Ferme la sidebar mobile en déléguant au toggle de la navbar (garde l'état synchronisé) */
  closeSidebar() {
    (document.querySelector('.navbar-toggler') as HTMLElement)?.click();
  }
}
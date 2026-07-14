import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit, ElementRef } from '@angular/core';
import { ROUTES } from '../sidebar/sidebar.component';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { Note, Utilisateur } from 'app/user-module/class/Utilisateur';
import { Session } from 'app/user-module/class/Session';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import { UtilisateurService } from 'app/user-module/service/utilisateur.service';
import { SessionService } from 'app/user-module/service/session.service';
import { MonCompteComponent } from 'app/user-module/components/mon-compte/mon-compte.component';
import { InscritService } from 'app/user-module/service/inscrit.service';
import { NoteMatriculeService } from 'app/services/note-matricule.service';
import { NotificationService, AppNotification } from 'app/services/notification.service';

const CURRENT_SESSION = "current_session";

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
    private listTitles: any[];
    location: Location;
    private toggleButton: any;
    private sidebarVisible: boolean;

    currentSession: Session;
    middleData: any = [{}];

    isLoggedIn = false;
    username: string;
    utilisateur: Utilisateur;

    notifications: AppNotification[] = [];
    get unreadCount(): number { return this.notificationService.unreadCount; }

    constructor(location: Location, private element: ElementRef, private router: Router, private tokenStorageService: TokenStorageService,
        public dialog: MatDialog,
        public utilisateurService: UtilisateurService,
        private notesService : NoteMatriculeService,
        private inscritService : InscritService,
        private sessionService: SessionService,
        public notificationService: NotificationService) {
        this.location = location;
        this.sidebarVisible = false;
    }

    ngOnInit() {
        this.listTitles = ROUTES.filter(listTitle => listTitle);
        this.notificationService.notifications$.subscribe(n => this.notifications = n);
        const navbar: HTMLElement = this.element.nativeElement;
        this.toggleButton = navbar.getElementsByClassName('navbar-toggler')[0];
        // Ferme la sidebar mobile à chaque navigation
        this.router.events.subscribe(() => this.sidebarClose());
    
        this.isLoggedIn = !!this.tokenStorageService.getToken();
        console.log("Login ?", this.isLoggedIn);
    
        if (this.isLoggedIn) {
            const user = this.tokenStorageService.getUser();
            console.log("User logged in:", user);
            this.username = user.username;
    
            // Récupérer les informations de l'utilisateur par username
            this.utilisateurService.findByUsername(this.username).subscribe(data => {
                if (data.payload && data.payload.length > 0) {
                    this.utilisateur = data.payload[0];
                    console.log('FINDBYUSERNAME', this.utilisateur);
    
                    // Ensuite, récupérer les notes de l'utilisateur via son matricule
                    this.utilisateurService.findByUserMatricule(this.utilisateur.username).subscribe((noteData: { payload: Note[] }) => {
                        if (noteData.payload && noteData.payload.length > 0) {
                            console.log('FINDBYUSERMATRICULE', noteData.payload);
                            
                            // Stocker les notes dans l'utilisateur
                            this.utilisateur.notes = noteData.payload.map(note => ({
                                semestre: note.semestre,
                                module: note.module,
                                libelle: note.libelle,
                                valeur: note.valeur
                            }));
    
                            // (Facultatif) stocker les notes dans le service si besoin
                            this.notesService.setNotes(this.utilisateur.notes);
    
                            // Vérification des notes stockées
                            console.log('Notes de l\'utilisateur:', this.utilisateur.notes);
                        } else {
                            console.log('Aucune note trouvée pour cet utilisateur');
                        }
                    });
                } else {
                    console.log('Utilisateur non trouvé');
                }
            });
        } else {
            this.router.navigate(['/connexion']);
        }
    }
    
    

    sidebarOpen() {
        document.body.classList.add('nav-open');
        this.toggleButton?.classList.add('toggled');
        this.sidebarVisible = true;

        // Overlay sombre cliquable derrière la sidebar.
        // Attaché à <body> : un ancêtre avec transform (ex: .main-panel du
        // template) détournerait le position:fixed de l'overlay.
        let layer = document.querySelector('.close-layer') as HTMLElement;
        if (!layer) {
            layer = document.createElement('div');
            layer.className = 'close-layer';
            document.body.appendChild(layer);
            layer.onclick = () => this.sidebarClose();
        }
        setTimeout(() => layer.classList.add('visible'), 50);
    };
    sidebarClose() {
        document.body.classList.remove('nav-open');
        this.toggleButton?.classList.remove('toggled');
        this.sidebarVisible = false;

        const layer = document.querySelector('.close-layer') as HTMLElement;
        if (layer) {
            layer.classList.remove('visible');
            setTimeout(() => layer.remove(), 350);
        }
    };
    sidebarToggle() {
        if (this.sidebarVisible) {
            this.sidebarClose();
        } else {
            this.sidebarOpen();
        }
    };

    getTitle() {
        var titlee = this.location.prepareExternalUrl(this.location.path());
        if (titlee.charAt(0) === '#') {
            titlee = titlee.slice(1);
        }

        for (var item = 0; item < this.listTitles.length; item++) {
            if (this.listTitles[item].path === titlee) {
                return this.listTitles[item].title;
            }
        }
        return 'Dashboard';
    }
    logout() {
        this.tokenStorageService.signOut();
        let code: string = localStorage.getItem(CURRENT_SESSION);
        this.sessionService.findByCode(code).subscribe(result => {
            this.middleData = result;
            this.middleData = this.middleData.payload[0];
            this.currentSession = this.middleData;
            this.currentSession.endDate = new Date();
            this.currentSession.statut = false;
            this.sessionService.update(this.currentSession).subscribe(result => {
                this.middleData = result;
            });
        });
        this.router.navigate(['/connexion']);
    }

    switchUser(username: string, password: string) {
        this.utilisateurService.signin({ username, password } as any).subscribe({
            next: (data: any) => {
                const payload = data.payload;
                const user = {
                    idUtilisateur:   payload.user?.idUtilisateur,
                    codeUtilisateur: payload.user?.codeUtilisateur,
                    username:        payload.user?.username,
                    nom:             payload.user?.nom,
                    prenom:          payload.user?.prenom,
                    email:           payload.user?.email,
                    type:            payload.user?.type,
                    profil:          payload.user?.profil,
                    role:            payload.role ? payload.role.split('#') : [],
                    codeSession:     payload.codeSession,
                };
                localStorage.setItem(CURRENT_SESSION, payload.codeSession);
                this.tokenStorageService.signOut();
                this.tokenStorageService.saveToken(payload.token);
                this.tokenStorageService.saveUser(user);
                window.location.href = '/dashboard';
            },
            error: () => console.warn('Impossible de changer de compte')
        });
    }

    updatePassWord() {
        this.dialog.open(MonCompteComponent, {
            width: '600px',
            data: { 'type': 'UPDATEPASSWORD', 'utilisateur': this.utilisateur }
        });
    }
    updateUtilisateur() {
        this.dialog.open(MonCompteComponent, {
            width: '600px',
            data: { 'type': 'UPDATEUSER', 'utilisateur': this.utilisateur }
        });
    }

    getUserInitials(): string {
        if (!this.username) return 'U';
        const parts = this.username.split('.');
        if (parts.length >= 2) return (parts[0][0] + parts[1][0]).toUpperCase();
        return this.username[0].toUpperCase();
    }

    getNavRoleLabel(): string {
        const type: string = this.tokenStorageService.getUser()?.type || '';
        switch (type) {
            case 'AGENT':   return 'Administrateur';
            case 'INSCRIT': return 'Étudiant(e)';
            case 'TUTEUR':  return 'Tuteur';
            default:        return '';
        }
    }

    markAllRead(): void {
        this.notificationService.markAllRead();
    }

    clearNotifications(): void {
        this.notificationService.clearAll();
    }

    notifIcon(type: string): string {
        switch (type) {
            case 'success': return 'check_circle';
            case 'error':   return 'error';
            case 'warning': return 'warning';
            default:        return 'info';
        }
    }

    notifColor(type: string): string {
        switch (type) {
            case 'success': return '#2E7D32';
            case 'error':   return '#C62828';
            case 'warning': return '#E65100';
            default:        return '#1565C0';
        }
    }

    formatTimestamp(date: Date): string {
        const d = new Date(date);
        const now = new Date();
        const diff = Math.floor((now.getTime() - d.getTime()) / 1000);
        if (diff < 60) return "À l'instant";
        if (diff < 3600) return `Il y a ${Math.floor(diff / 60)} min`;
        if (diff < 86400) return `Il y a ${Math.floor(diff / 3600)} h`;
        return d.toLocaleDateString('fr-FR', { day: '2-digit', month: 'short' });
    }
}

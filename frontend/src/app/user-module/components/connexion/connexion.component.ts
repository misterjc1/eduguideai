import { CredentialVo } from './../../class/CredentialVo';
import { Component, OnInit } from '@angular/core';
import { UtilisateurService } from 'app/user-module/service/utilisateur.service';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import { Router } from '@angular/router';

const CURRENT_SESSION = 'current_session';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.scss']
})
export class ConnexionComponent implements OnInit {

  credential: CredentialVo;
  isLoginFailed = false;
  errorMessage = '';
  error = false;
  hide = true;
  isLoading = false;

  demoAccounts = [
    { username: 'admin',           password: 'admin123',   role: 'Administrateur',  icon: 'admin_panel_settings', color: '#1565C0' },
    { username: 'alice.kabore',    password: 'etud123',    role: 'Étudiante',        icon: 'school',               color: '#2E7D32' },
    { username: 'moussa.sawadogo', password: 'etud123',    role: 'Étudiant',         icon: 'school',               color: '#2E7D32' },
    { username: 'traore.parent',   password: 'parent123',  role: 'Tuteur / Parent',  icon: 'family_restroom',      color: '#E65100' },
    { username: 'prof.ouedraogo',  password: 'prof123',    role: 'Enseignant',       icon: 'person',               color: '#6A1B9A' },
  ];

  fillAndSubmit(account: { username: string; password: string }) {
    this.credential.username = account.username;
    this.credential.password = account.password;
    this.onSubmit();
  }

  constructor(
    private utilisateurService: UtilisateurService,
    private tokenStorage: TokenStorageService,
    private router: Router,
  ) {
    this.credential = new CredentialVo();
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.router.navigateByUrl('/dashboard');
    }
  }

  onSubmit() {
    if (!this.credential.username || !this.credential.password) {
      this.error = true;
      this.errorMessage = 'Veuillez renseigner votre nom d\'utilisateur et votre mot de passe.';
      return;
    }

    this.isLoading = true;
    this.error = false;

    this.utilisateurService.signin(this.credential).subscribe({
      next: data => {
        this.isLoading = false;
        const payload = data.payload;

        // Le backend renvoie : { token, user, role, codeSession, referenceExterne }
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
        this.tokenStorage.saveToken(payload.token);
        this.tokenStorage.saveUser(user);

        this.router.navigateByUrl('/dashboard');
      },
      error: () => {
        this.isLoading = false;
        this.error = true;
        this.errorMessage = 'Nom d\'utilisateur ou mot de passe incorrect.';
      }
    });
  }

  closeError() {
    this.error = false;
  }
}

import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

/**
 * Barre de navigation compacte affichée en haut des pages service :
 * un bouton retour (page précédente) et un bouton accueil (dashboard).
 */
@Component({
  selector: 'app-back-button',
  template: `
    <div class="ega-back-bar">
      <button class="ega-back-btn" type="button" (click)="goBack()" aria-label="Revenir en arrière">
        <mat-icon>arrow_back</mat-icon>
        <span>Retour</span>
      </button>
      <button class="ega-back-btn" type="button" (click)="goHome()" aria-label="Retourner à l'accueil">
        <mat-icon>home</mat-icon>
        <span>Accueil</span>
      </button>
    </div>
  `,
  styles: [`
    .ega-back-bar {
      display: flex;
      gap: 8px;
      padding: 10px 14px 0;
    }
    .ega-back-btn {
      display: inline-flex;
      align-items: center;
      gap: 6px;
      padding: 6px 14px 6px 10px;
      background: #ffffff;
      border: 1px solid #E2E8F0;
      border-radius: 20px;
      color: #475569;
      font-size: 13px;
      font-weight: 600;
      font-family: 'Inter', 'Roboto', sans-serif;
      cursor: pointer;
      transition: background 0.15s, color 0.15s, border-color 0.15s;
      -webkit-tap-highlight-color: transparent;
    }
    .ega-back-btn:hover,
    .ega-back-btn:active {
      background: #EFF6FF;
      color: #1565C0;
      border-color: #BFDBFE;
    }
    .ega-back-btn mat-icon {
      font-size: 18px;
      width: 18px;
      height: 18px;
    }
  `]
})
export class BackButtonComponent {
  constructor(private location: Location, private router: Router) {}

  goBack(): void {
    this.location.back();
  }

  goHome(): void {
    this.router.navigate(['/dashboard']);
  }
}

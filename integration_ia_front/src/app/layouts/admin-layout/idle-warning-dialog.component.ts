import { Component, Inject, OnInit, OnDestroy } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-idle-warning-dialog',
  template: `
    <div class="iw-root">
      <div class="iw-icon-wrap">
        <mat-icon class="iw-icon">schedule</mat-icon>
      </div>
      <h2 class="iw-title">Session inactive</h2>
      <p class="iw-msg">
        Vous serez déconnecté automatiquement dans
      </p>
      <div class="iw-countdown">{{ seconds }}</div>
      <p class="iw-msg-sub">secondes</p>
      <div class="iw-actions">
        <button class="iw-btn iw-btn-logout" (click)="choose('logout')">
          <mat-icon>logout</mat-icon> Se déconnecter
        </button>
        <button class="iw-btn iw-btn-stay" (click)="choose('stay')">
          <mat-icon>refresh</mat-icon> Rester connecté
        </button>
      </div>
    </div>
  `,
  styles: [`
    .iw-root {
      text-align: center;
      padding: 28px 24px 20px;
      font-family: 'Inter','Roboto',sans-serif;
    }
    .iw-icon-wrap {
      width: 64px; height: 64px; border-radius: 50%;
      background: #FFF3E0; display: flex; align-items: center;
      justify-content: center; margin: 0 auto 16px;
    }
    .iw-icon { font-size: 36px; width: 36px; height: 36px; color: #E65100; }
    .iw-title { font-size: 18px; font-weight: 700; color: #1E293B; margin: 0 0 8px; }
    .iw-msg { font-size: 13.5px; color: #64748B; margin: 0; }
    .iw-msg-sub { font-size: 12px; color: #94A3B8; margin: 4px 0 20px; }
    .iw-countdown {
      font-size: 56px; font-weight: 800; color: #E65100;
      line-height: 1; margin: 10px 0 4px;
    }
    .iw-actions { display: flex; gap: 10px; justify-content: center; flex-wrap: wrap; }
    .iw-btn {
      display: flex; align-items: center; gap: 6px;
      padding: 9px 18px; border-radius: 8px; border: none;
      font-size: 13.5px; font-weight: 600; cursor: pointer;
      font-family: inherit; transition: all 0.15s;
    }
    .iw-btn mat-icon { font-size: 18px; width: 18px; height: 18px; }
    .iw-btn-stay { background: #1565C0; color: #fff; }
    .iw-btn-stay:hover { background: #1251A3; }
    .iw-btn-logout { background: #F1F5F9; color: #374151; }
    .iw-btn-logout:hover { background: #E2E8F0; }
  `]
})
export class IdleWarningDialogComponent implements OnInit, OnDestroy {
  seconds = 120;

  constructor(
    private dialogRef: MatDialogRef<IdleWarningDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { seconds: number }
  ) {
    this.seconds = data?.seconds ?? 120;
  }

  ngOnInit(): void {}

  // Called by parent with updated seconds from IdleService
  updateSeconds(s: number): void {
    this.seconds = s;
    if (this.seconds <= 0) this.choose('timeout');
  }

  choose(result: 'stay' | 'logout' | 'timeout'): void {
    this.dialogRef.close(result);
  }

  ngOnDestroy(): void {}
}

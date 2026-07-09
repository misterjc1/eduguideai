import { Injectable, NgZone } from '@angular/core';
import { Subject } from 'rxjs';

const IDLE_MINUTES   = 15;  // inactivité avant avertissement
const WARNING_SECONDS = 120; // secondes pour agir avant déconnexion

@Injectable({ providedIn: 'root' })
export class IdleService {

  private warning$  = new Subject<number>(); // secondes restantes
  private timeout$  = new Subject<void>();

  private lastActivity = Date.now();
  private ticker: any   = null;
  private isWarning     = false;
  private warningFrom   = 0;

  readonly idleMs    = IDLE_MINUTES * 60 * 1000;
  readonly warningMs = WARNING_SECONDS * 1000;

  get onWarning() { return this.warning$.asObservable(); }
  get onTimeout() { return this.timeout$.asObservable(); }

  constructor(private zone: NgZone) {}

  start(): void {
    const events = ['mousemove', 'mousedown', 'click', 'keydown', 'touchstart', 'scroll'];
    events.forEach(e => document.addEventListener(e, () => this.onActivity(), { passive: true }));
    this.zone.runOutsideAngular(() => {
      this.ticker = setInterval(() => this.check(), 1000);
    });
  }

  stop(): void {
    if (this.ticker) { clearInterval(this.ticker); this.ticker = null; }
  }

  resetTimer(): void {
    this.lastActivity = Date.now();
    this.isWarning    = false;
  }

  private onActivity(): void {
    if (!this.isWarning) this.lastActivity = Date.now();
  }

  private check(): void {
    const elapsed = Date.now() - this.lastActivity;

    if (this.isWarning) {
      const secondsLeft = Math.ceil((this.warningMs - (Date.now() - this.warningFrom)) / 1000);
      if (secondsLeft <= 0) {
        this.zone.run(() => this.timeout$.next());
        this.stop();
      } else {
        this.zone.run(() => this.warning$.next(secondsLeft));
      }
      return;
    }

    if (elapsed >= this.idleMs) {
      this.isWarning  = true;
      this.warningFrom = Date.now();
      this.zone.run(() => this.warning$.next(WARNING_SECONDS));
    }
  }
}

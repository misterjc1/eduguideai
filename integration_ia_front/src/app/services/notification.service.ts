import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface AppNotification {
  id: string;
  title: string;
  message: string;
  type: 'success' | 'error' | 'warning' | 'info';
  timestamp: Date;
  read: boolean;
}

const STORAGE_KEY = 'app_notifications';
const MAX_NOTIFICATIONS = 30;

@Injectable({ providedIn: 'root' })
export class NotificationService {
  private _notifications$ = new BehaviorSubject<AppNotification[]>(this.load());
  notifications$ = this._notifications$.asObservable();

  get unreadCount(): number {
    return this._notifications$.value.filter(n => !n.read).length;
  }

  add(title: string, message: string, type: 'success' | 'error' | 'warning' | 'info' = 'info'): void {
    const n: AppNotification = {
      id: Date.now().toString(),
      title,
      message,
      type,
      timestamp: new Date(),
      read: false
    };
    const updated = [n, ...this._notifications$.value].slice(0, MAX_NOTIFICATIONS);
    this.save(updated);
    this._notifications$.next(updated);
  }

  markAllRead(): void {
    const updated = this._notifications$.value.map(n => ({ ...n, read: true }));
    this.save(updated);
    this._notifications$.next(updated);
  }

  clearAll(): void {
    this.save([]);
    this._notifications$.next([]);
  }

  private load(): AppNotification[] {
    try {
      const raw = localStorage.getItem(STORAGE_KEY);
      if (!raw) return [];
      const parsed = JSON.parse(raw);
      return parsed.map((n: any) => ({ ...n, timestamp: new Date(n.timestamp) }));
    } catch {
      return [];
    }
  }

  private save(notifications: AppNotification[]): void {
    try {
      localStorage.setItem(STORAGE_KEY, JSON.stringify(notifications));
    } catch {}
  }
}

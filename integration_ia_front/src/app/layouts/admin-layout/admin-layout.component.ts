import { Component, OnInit, OnDestroy, ViewChild, AfterViewInit } from '@angular/core';
import { Location, PopStateEvent } from '@angular/common';
import { Router, NavigationEnd, NavigationStart } from '@angular/router';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { filter, Subscription } from 'rxjs';
import PerfectScrollbar from 'perfect-scrollbar';
import * as $ from 'jquery';
import { IdleService } from 'app/services/idle.service';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import { IdleWarningDialogComponent } from './idle-warning-dialog.component';

const CURRENT_SESSION = 'current_session';

@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  styleUrls: ['./admin-layout.component.scss']
})
export class AdminLayoutComponent implements OnInit, OnDestroy, AfterViewInit {
  private _router: Subscription;
  private lastPoppedUrl: string;
  private yScrollStack: number[] = [];
  private subs: Subscription[] = [];
  private warningDialog: MatDialogRef<IdleWarningDialogComponent> | null = null;

  constructor(
    public  location:       Location,
    private router:         Router,
    private dialog:         MatDialog,
    private idleService:    IdleService,
    private tokenStorage:   TokenStorageService,
  ) {}

  ngOnInit(): void {
    // ── Scroll position restauration ──────────────────────────
    const isWindows = navigator.platform.indexOf('Win') > -1;
    if (isWindows && !document.getElementsByTagName('body')[0].classList.contains('sidebar-mini')) {
      document.getElementsByTagName('body')[0].classList.add('perfect-scrollbar-on');
    } else {
      document.getElementsByTagName('body')[0].classList.remove('perfect-scrollbar-off');
    }

    const elemMainPanel = document.querySelector('.main-panel') as HTMLElement;
    const elemSidebar   = document.querySelector('.sidebar .sidebar-wrapper') as HTMLElement;

    this.location.subscribe((ev: PopStateEvent) => { this.lastPoppedUrl = ev.url; });

    this.router.events.subscribe((event: any) => {
      if (event instanceof NavigationStart) {
        if (event.url !== this.lastPoppedUrl) this.yScrollStack.push(window.scrollY);
      } else if (event instanceof NavigationEnd) {
        if (event.url === this.lastPoppedUrl) {
          this.lastPoppedUrl = undefined;
          window.scrollTo(0, this.yScrollStack.pop());
        } else {
          window.scrollTo(0, 0);
        }
      }
    });

    this._router = this.router.events
      .pipe(filter(e => e instanceof NavigationEnd))
      .subscribe(() => {
        if (elemMainPanel) elemMainPanel.scrollTop = 0;
        if (elemSidebar)   elemSidebar.scrollTop   = 0;
      });

    if (window.matchMedia('(min-width: 960px)').matches && !this.isMac()) {
      if (elemMainPanel) new PerfectScrollbar(elemMainPanel);
      if (elemSidebar)   new PerfectScrollbar(elemSidebar);
    }

    this.setupJQueryPlugins();

    // ── Déconnexion automatique après inactivité ───────────────
    this.idleService.start();

    const warnSub = this.idleService.onWarning.subscribe(seconds => {
      if (this.warningDialog) {
        // Mettre à jour le compte à rebours dans le dialogue ouvert
        this.warningDialog.componentInstance?.updateSeconds(seconds);
      } else {
        // Ouvrir le dialogue d'avertissement
        this.warningDialog = this.dialog.open(IdleWarningDialogComponent, {
          data:         { seconds },
          disableClose: true,
          width:        '380px',
          panelClass:   'idle-dialog-panel',
        });
        this.warningDialog.afterClosed().subscribe((result: string) => {
          this.warningDialog = null;
          if (result === 'stay') {
            this.idleService.resetTimer();
          } else {
            // logout ou timeout
            this.doLogout();
          }
        });
      }
    });

    const timeoutSub = this.idleService.onTimeout.subscribe(() => {
      if (this.warningDialog) {
        this.warningDialog.close('timeout');
      } else {
        this.doLogout();
      }
    });

    this.subs.push(warnSub, timeoutSub);
  }

  ngAfterViewInit(): void {
    this.runOnRouteChange();
  }

  ngOnDestroy(): void {
    this.idleService.stop();
    this.subs.forEach(s => s.unsubscribe());
    if (this._router) this._router.unsubscribe();
  }

  private doLogout(): void {
    this.idleService.stop();
    this.tokenStorage.signOut();
    localStorage.removeItem(CURRENT_SESSION);
    this.router.navigate(['/connexion']);
  }

  // ── Utilitaires ────────────────────────────────────────────
  isMaps(path: string): boolean {
    const titlee = this.location.prepareExternalUrl(this.location.path()).slice(1);
    return path !== titlee;
  }

  runOnRouteChange(): void {
    if (window.matchMedia('(min-width: 960px)').matches && !this.isMac()) {
      const elemMainPanel = document.querySelector('.main-panel') as HTMLElement;
      if (elemMainPanel) { const ps = new PerfectScrollbar(elemMainPanel); ps.update(); }
    }
  }

  isMac(): boolean {
    return navigator.platform.toUpperCase().includes('MAC') || navigator.platform.toUpperCase().includes('IPAD');
  }

  private setupJQueryPlugins(): void {
    const $sidebar           = $('.sidebar');
    const $sidebar_responsive = $('body > .navbar-collapse');
    const $sidebar_img_container = $sidebar.find('.sidebar-background');
    const window_width = $(window).width();

    if (window_width > 767 && $('.fixed-plugin .dropdown').hasClass('show-dropdown')) {
      $('.fixed-plugin .dropdown').addClass('open');
    }

    $('.fixed-plugin a').click(function (event: any) {
      if ($(this).hasClass('switch-trigger')) {
        if (event.stopPropagation) event.stopPropagation();
        else if ((window as any).event) (window as any).event.cancelBubble = true;
      }
    });

    $('.fixed-plugin .badge').click(function () {
      $(this).siblings().removeClass('active');
      $(this).addClass('active');
      const new_color = $(this).data('color');
      if ($sidebar.length) $sidebar.attr('data-color', new_color);
      if ($sidebar_responsive.length) $sidebar_responsive.attr('data-color', new_color);
    });

    $('.fixed-plugin .img-holder').click(function () {
      $(this).parent('li').siblings().removeClass('active');
      $(this).parent('li').addClass('active');
      const new_image = $(this).find('img').attr('src');
      if ($sidebar_img_container.length) {
        $sidebar_img_container.fadeOut('fast', function () {
          $sidebar_img_container.css('background-image', `url("${new_image}")`);
          $sidebar_img_container.fadeIn('fast');
        });
      }
      if ($sidebar_responsive.length) {
        $sidebar_responsive.css('background-image', `url("${new_image}")`);
      }
    });
  }
}

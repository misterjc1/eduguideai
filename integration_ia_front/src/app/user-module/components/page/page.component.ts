import { Page } from './../../class/Page';
import { Component, OnInit } from '@angular/core';
import { TreeViewMetaData } from 'app/user-module/layouts/treeView.model';
import { PageService } from 'app/user-module/service/page.service';
import { MatDialog } from '@angular/material/dialog';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

export class PageNode {
  children: PageNode[];
  page: Page;
}

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.scss']
})
export class PageComponent implements OnInit {

  page: Page[] = [];
  tabData: any[] = [];
  public nodeData = new TreeViewMetaData();
  isDataLoaded: boolean = false;
  errorMessage: string = '';

  constructor(private pageService: PageService, public dialog: MatDialog) {}

  ngOnInit() {
    this.pageService.findAll().pipe(
      catchError(() => {
        this.errorMessage = 'Impossible de charger les rôles.';
        return of({ payload: [] } as any);
      })
    ).subscribe((data: any) => {
      const payload = data?.payload || [];
      this.page = payload;
      this.tabData = payload;
      this.nodeData.type = 'PAGE';
      this.nodeData.columnToShow = 'intitule';
      this.nodeData.data = payload;
      this.nodeData.parentRow = 'father';
      this.nodeData.rowKey = 'codePage';
      this.nodeData.title = 'Liste des rôles';
      this.isDataLoaded = true;
    });
  }
}

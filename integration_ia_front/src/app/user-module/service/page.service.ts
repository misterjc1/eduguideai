import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DeleteVo } from '../class/DeleteVo';
import { MTResponse } from '../class/MTResponse';
import { Page } from '../class/Page';
import { ADD_PAGE, DELETE_PAGE, FIND_ALL_PAGE, FIND_PAGE_BY_KEY, UPDATE_PAGE } from '../helpers/url.constants';

@Injectable({
  providedIn: 'root'
})
export class PageService {
  constructor(private http: HttpClient) { }

  public findAll (): Observable<MTResponse<Page[]>> {
    return this.http.get<MTResponse<Page[]>>(FIND_ALL_PAGE);
  }

  public save(page: Page) {
    return this.http.post(ADD_PAGE, page);
  }

  public update(page: Page) {
    return this.http.post(UPDATE_PAGE, page);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_PAGE, deleteVo);
  }

  public findPageByCode(codePage: any) {
    return this.http.get(FIND_PAGE_BY_KEY + codePage);
  }
}


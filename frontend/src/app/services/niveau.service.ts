import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DeleteVo } from 'app/class/DeleteVo';
import { Niveau } from 'app/class/Niveau';
import { DELETE_NIVEAU, FIND_ALL_NIVEAUINSCRIT, FIND_NIVEAU_BY_CODE, SAVE_NIVEAU, UPDATE_NIVEAU } from 'app/helpers/url.constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NiveauService {
  constructor(private http: HttpClient) { }

  public findAll(): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_ALL_NIVEAUINSCRIT);
  }

  public findByCode(codeNiveau): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_NIVEAU_BY_CODE + codeNiveau);
  }

  public save(niveau: Niveau) {
    return this.http.post(SAVE_NIVEAU, niveau);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_NIVEAU, deleteVo);
  }

  public update(niveau: Niveau) {
    return this.http.post(UPDATE_NIVEAU, niveau);
  }
}

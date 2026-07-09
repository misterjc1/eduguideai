import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DeleteVo } from 'app/class/DeleteVo';
import { Donnees } from 'app/class/Donnees';
import { DELETE_DONNEES, FIND_ALL_DONNEES, FIND_DONNEES_BY_CODE, SAVE_DONNEES, UPDATE_DONNEES } from 'app/helpers/url.constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DonneesService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_ALL_DONNEES);
  }

  public save(donnees: Donnees) {
    return this.http.post(SAVE_DONNEES, donnees);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_DONNEES, deleteVo);
  }

  public update(donnees: Donnees) {
    return this.http.post(UPDATE_DONNEES, donnees);
  }

  public findByCode(codeDonnees: string): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_DONNEES_BY_CODE + codeDonnees);
  }
  
}


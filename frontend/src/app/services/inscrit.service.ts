import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DeleteVo } from 'app/class/DeleteVo';
import { Inscrit } from 'app/class/Inscrit';
import { DELETE_INSCRIT, FIND_ALL_INSCRIT, FIND_INSCRIT_BY_CODE, SAVE_INSCRIT, UPDATE_INSCRIT } from 'app/helpers/url.constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InscritService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_ALL_INSCRIT);
  }

  public save(inscrit: Inscrit) {
    return this.http.post(SAVE_INSCRIT, inscrit);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_INSCRIT, deleteVo);
  }

  public update(inscrit: Inscrit) {
    return this.http.post(UPDATE_INSCRIT, inscrit);
  }

  public findByCode(codeInscrit: string): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_INSCRIT_BY_CODE + codeInscrit);
  }
  
}



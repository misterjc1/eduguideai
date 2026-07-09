import { FIND_PERSONNE_BY_STRUCTURE } from './../helpers/url.constants';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Response } from 'app/class/Response';
import { DELETE_PERSONNE, FIND_ALL_PERSONNE, FIND_PERSONNE_BY_CODE, SAVE_PERSONNE, UPDATE_PERSONNE } from '../helpers/url.constants';
import { DeleteVo } from '../class/DeleteVo';
import { Personne } from '../class/Personne';

@Injectable({
  providedIn: 'root'
})
export class PersonneService {

constructor(private http: HttpClient) { }

  public findAll(): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_ALL_PERSONNE);
  }

  public findByCode(codePersonne): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_PERSONNE_BY_CODE + codePersonne);
  }

  public findByStructure(code): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_PERSONNE_BY_STRUCTURE + code);
  }

  public save(personne: Personne) {
    return this.http.post(SAVE_PERSONNE, personne);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_PERSONNE, deleteVo);
  }

  public update(personne: Personne) {
    return this.http.post(UPDATE_PERSONNE, personne);
  }

}

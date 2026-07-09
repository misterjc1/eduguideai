import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DeleteVo } from 'app/class/DeleteVo';
import { Liaison } from 'app/class/Liaison';
import { DELETE_LIAISON, FIND_ALL_LIAISON, FIND_ALL_LIAISON_VALIDE, FIND_LIAISON_BY_CODE, FIND_LIAISON_BY_CODE_UTILISATEUR, SAVE_LIAISON, UPDATE_LIAISON } from 'app/helpers/url.constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LiaisonService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_ALL_LIAISON);
  }

  public save(liaison: Liaison) {
    return this.http.post(SAVE_LIAISON, liaison);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_LIAISON, deleteVo);
  }

  public update(liaison: Liaison) {
    return this.http.post(UPDATE_LIAISON, liaison);
  }

  public findByCode(codeLiaison: string): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_LIAISON_BY_CODE + codeLiaison);
  }
  
  public findLiaisonByCodeUtilisateur(codeUtilisateur: string, statutLiaison: string): Observable<Response[]> {
    const url = FIND_LIAISON_BY_CODE_UTILISATEUR + codeUtilisateur + '/' + statutLiaison;
    return this.http.get<Response[]>(url);
}


  public getLiaisonValide(statutLiaison): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_ALL_LIAISON_VALIDE + statutLiaison);
  }
}



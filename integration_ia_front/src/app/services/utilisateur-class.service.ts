import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DeleteVo } from 'app/class/DeleteVo';
import { UtilisateurClass } from 'app/class/UtilisateurClass';
import { DELETE_UTILISATEUR, FIND_ALL_UTILISATEUR, FIND_UTILISATEUR_BY_CODE, GENERATE_PINUTILISATEUR, SAVE_UTILISATEUR, UPDATE_UTILISATEUR } from 'app/helpers/url.constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurClassService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_ALL_UTILISATEUR);
  }
  public findByCode(codeUtilisateur): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_UTILISATEUR_BY_CODE + codeUtilisateur);
  }

  public save(utilisateurClass: UtilisateurClass) {
    return this.http.post(SAVE_UTILISATEUR, utilisateurClass);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_UTILISATEUR, deleteVo);
  }

  public update(utilisateurClass: UtilisateurClass) {
    return this.http.post(UPDATE_UTILISATEUR, utilisateurClass);
  }

  public generatePin(utilisateurClass: UtilisateurClass) {
    return this.http.post(GENERATE_PINUTILISATEUR, utilisateurClass);
  }
  
}
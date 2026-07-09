import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DeleteVo } from 'app/class/DeleteVo';
import { DELETE_UTILISATEUR, FIND_ALL_UTILISATEUR, FIND_UTILISATEUR_BY_CODE, GENERATE_PIN, SAVE_UTILISATEUR, UPDATE_UTILISATEUR } from 'app/helpers/url.constants';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_ALL_UTILISATEUR);
  }

  public save(utilisateur: Utilisateur) {
    return this.http.post(SAVE_UTILISATEUR, utilisateur);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_UTILISATEUR, deleteVo);
  }

  public update(utilisateur: Utilisateur) {
    return this.http.post(UPDATE_UTILISATEUR, utilisateur);
  }

  public findByCode(codeUtilisateur: string): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_UTILISATEUR_BY_CODE + codeUtilisateur);
  }

  public generatePin(utilisateur: Utilisateur) {
    return this.http.post(GENERATE_PIN, utilisateur);
  }

}


import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DeleteVo } from 'app/class/DeleteVo';
import { Parametre } from 'app/class/Parametre';
import { ADD_PARAMETRE, DELETE_PARAMETRE, FIND_ALL_PARAMETRE, FIND_PARAMETRE_BY_CODE, UPDATE_PARAMETRE } from 'app/helpers/url.constants';
import { MTResponse } from 'app/user-module/class/MTResponse';
import { log } from 'console';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ParametreService {

  private readonly API_URL  = 'http://localhost:8181/niveau';
  private readonly API_URL1 = 'http://localhost:8181/note';




  constructor(private http: HttpClient) { }

  //trouver les parametres enregistrer en locals 
  /*public findAll(): Observable<MTResponse<Parametre[]>> {
    return this.http.get<MTResponse<Parametre[]>>(FIND_ALL_PARAMETRE);
  }*/

  // Récupère tous les niveaux depuis l'api chez ramzi
  public findAll(): Observable<MTResponse<any[]>> {
    return this.http.get<MTResponse<any[]>>(`${this.API_URL}/findAll`);
  }

  //recupere les notes
  // Récupère tous les niveaux depuis l'api chez ramzi
  public findAllNote(): Observable<MTResponse<any[]>> {
    return this.http.get<MTResponse<any[]>>(`${this.API_URL1}/findAll`);
  }

  public findByCode(codeParametre): Observable<MTResponse<Parametre[]>> {
    return this.http.get<MTResponse<Parametre[]>>(FIND_PARAMETRE_BY_CODE + codeParametre);
  }

  public save(parametre: Parametre) {
    return this.http.post(ADD_PARAMETRE, parametre);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_PARAMETRE, deleteVo);
  }

  public update(parametre: Parametre) {
    return this.http.post(UPDATE_PARAMETRE, parametre);
  }

}



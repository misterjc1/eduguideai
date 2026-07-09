import { FIND_PROFIL_BY_CODE } from './../helpers/url.constants';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Profil } from './../class/Profil';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Response } from 'app/class/Response';
import { ADD_PROFIL, DELETE_PROFIL, FIND_ALL_PROFIL, UPDATE_PROFIL } from '../helpers/url.constants';
import { DeleteVo } from '../class/DeleteVo';
import { MTResponse } from '../class/MTResponse';



@Injectable({
  providedIn: 'root'
})
export class ProfilService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<MTResponse<Profil[]>> {
    return this.http.get<MTResponse<Profil[]>>(FIND_ALL_PROFIL);
  }

  public findByCode(codeProfil): Observable<MTResponse<Profil[]>> {
    return this.http.get<MTResponse<Profil[]>>(FIND_PROFIL_BY_CODE + codeProfil);
  }

  public save(personne: Profil) {
    return this.http.post(ADD_PROFIL, personne);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_PROFIL, deleteVo);
  }

  public update(personne: Profil) {
    return this.http.post(UPDATE_PROFIL, personne);
  }

}

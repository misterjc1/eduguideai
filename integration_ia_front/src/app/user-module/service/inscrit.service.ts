import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MTResponse } from '../class/MTResponse';
import { Utilisateur } from '../class/Utilisateur';
import { Observable } from 'rxjs';
import { FIND_ALL_INSCRITS, FIND_NOTES_BY_MATRICULE } from '../helpers/url.constants';

@Injectable({
  providedIn: 'root'
})
export class InscritService {
  private baseUrl = FIND_NOTES_BY_MATRICULE;


  constructor(private http: HttpClient) { }

  public findAllInscrits(): Observable<MTResponse<Utilisateur[]>> {
    return this.http.get<MTResponse<Utilisateur[]>>(FIND_ALL_INSCRITS);
  }

  public findByUserMatricule(username: string): Observable<MTResponse<Utilisateur[]>> {
    // Construction de l'URL avec le matricule (username ici)
    return this.http.get<MTResponse<Utilisateur[]>>(`${this.baseUrl}${username}`);
  }
}

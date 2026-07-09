import { UPDATEPASSWORD, FIND_UTILISATEUR_BY_USERNAME, FIND_UTILISATEUR_BY_CODE, FIND_UTILISATEUR_BY_STRUCTURE,FIND_ALL_INSCRITS, FIND_NOTES_BY_MATRICULE } from './../helpers/url.constants';
import { CredentialUpdateVo } from './../class/CredentialUpdateVo';
import { CredentialVo } from './../class/CredentialVo';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Response } from 'app/class/Response';
import { Observable } from 'rxjs';
import { DeleteVo } from '../class/DeleteVo';
import { Note, Utilisateur } from '../class/Utilisateur';
import { ADD_UTILISATEUR, DELETE_UTILISATEUR, FIND_ALL_UTILISATEUR, UPDATE_UTILISATEUR ,SIGN_IN, SIGN_UP, FIND_UTILISATEUR_BY_PERSON} from '../helpers/url.constants';
import { MTResponse } from '../class/MTResponse';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {
  private baseUrl = FIND_NOTES_BY_MATRICULE;


constructor(private http: HttpClient) { }

  public findAll(): Observable<MTResponse<Utilisateur[]>> {
    return this.http.get<MTResponse<Utilisateur[]>>(FIND_ALL_UTILISATEUR);

  }

  public findAllInscrits(): Observable<MTResponse<Utilisateur[]>> {
    return this.http.get<MTResponse<Utilisateur[]>>(FIND_ALL_INSCRITS);
  }

  public findByCode(codeUser): Observable<MTResponse<Utilisateur>> {
    return this.http.get<MTResponse<Utilisateur>>(FIND_UTILISATEUR_BY_CODE + codeUser);
  }

  public findUserByStructure(codeStructure): Observable<MTResponse<Utilisateur>> {
    return this.http.get<MTResponse<Utilisateur>>(FIND_UTILISATEUR_BY_STRUCTURE + codeStructure);
  }

  public findByPerson(codePersonne): Observable<MTResponse<Utilisateur[]>> {
    return this.http.get<MTResponse<Utilisateur[]>>(FIND_UTILISATEUR_BY_PERSON + codePersonne);
  }
  public findByUsername(username): Observable<MTResponse<Utilisateur[]>> {
    return this.http.get<MTResponse<Utilisateur[]>>(FIND_UTILISATEUR_BY_USERNAME + username);
  }

  /*public findByUserMatricule(username: string): Observable<MTResponse<Utilisateur[]>> {
    // Construction de l'URL avec le matricule (username ici)
    return this.http.get<MTResponse<Utilisateur[]>>(`${this.baseUrl}${username}`);
  }*/

    findByUserMatricule(username: string): Observable<MTResponse<Note[]>> {
      return this.http.get<MTResponse<Note[]>>(`${this.baseUrl}${username}`);
  }

  // Nouvelle méthode pour récupérer les notes par matricule ajouté 16/10
  /*public findNotesByMatricule(matricule: string): Observable<MTResponse<any>> {
    return this.http.get<MTResponse<any>>(`${FIND_NOTES_BY_MATRICULE}/${matricule}`, httpOptions);
  }*/
  


  public save(utilisateur: Utilisateur) {
    return this.http.post(ADD_UTILISATEUR, utilisateur);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_UTILISATEUR, deleteVo);
  }

  public update(utilisateur: Utilisateur) {
    return this.http.post(UPDATE_UTILISATEUR, utilisateur);
  }
  public signin(credential: CredentialVo) : Observable<any> {
    return this.http.post(SIGN_IN, {
      username: credential.username,
      password: credential.password
    },httpOptions);
  }
  public updatePassWord(credentialUpdateVo: CredentialUpdateVo) : Observable<any> {
    return this.http.post(UPDATEPASSWORD, credentialUpdateVo);
  }
  public signup(utilisateur: Utilisateur) {
    return this.http.post(SIGN_UP, utilisateur, httpOptions);
  }

}

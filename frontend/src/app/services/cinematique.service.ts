import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cinematique } from 'app/class/Cinematique';
import { DeleteVo } from 'app/class/DeleteVo';
import { Niveau } from 'app/class/Enums/Niveau';
import { DELETE_CINEMATIQUE, FIND_ALL_CINEMATIQUE, FIND_CINEMATIQUE_BY_CODE, FIND_CINEMATIQUE_BY_NIVEAU, FIND_CINEMATIQUE_IMAGE, SAVE_CINEMATIQUE, UPDATE_CINEMATIQUE } from 'app/helpers/url.constants';
import { map, Observable } from 'rxjs';

interface ApiResponse {
  payload: Cinematique[];
  message: string;
  status: string;
}



@Injectable({
  providedIn: 'root'
})
export class CinematiqueService {

  constructor(private http: HttpClient) { }

  //public findAll(): Observable<Response[]> {
   // return this.http.get<Response[]>(FIND_ALL_CINEMATIQUE);
  //}

  public save(cinematique: Cinematique) {
    return this.http.post(SAVE_CINEMATIQUE, cinematique);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_CINEMATIQUE, deleteVo);
  }

  public update(cinematique: Cinematique) {
    return this.http.post(UPDATE_CINEMATIQUE, cinematique);
  }

  public findByCode(codeCinematique: string): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_CINEMATIQUE_BY_CODE + codeCinematique);
  }
  public findAll(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(FIND_ALL_CINEMATIQUE);
  }

  public findByNiveau(niveau: string): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_CINEMATIQUE_BY_NIVEAU + niveau);
  }

  // Filtrer les cinématiques par niveau (ici, NIVEAU2)
  public findCinematiquesByNiveau(niveau: Niveau): Observable<Cinematique[]> {
    return this.findAll().pipe(
      map(response => 
        response.payload.filter(cinematique => 
          cinematique.typeCinematique && cinematique.typeCinematique.niveau === niveau && !cinematique.deleted
        )
      )
    );
  }

  public findImage(imageUrl: string) {
    return this.http.get(FIND_CINEMATIQUE_IMAGE + imageUrl);
  }
  
}
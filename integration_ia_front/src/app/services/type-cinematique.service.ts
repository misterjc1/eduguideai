import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DeleteVo } from 'app/class/DeleteVo';
import { TypeCinematique } from 'app/class/TypeCinematique';
import { DELETE_TYPECINEMATIQUE, FIND_ALL_TYPECINEMATIQUE, FIND_TYPECINEMATIQUE_BY_CODE, SAVE_TYPECINEMATIQUE, UPDATE_TYPECINEMATIQUE } from 'app/helpers/url.constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TypeCinematiqueService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_ALL_TYPECINEMATIQUE);
  }

  public save(typeCinematique: TypeCinematique) {
    return this.http.post(SAVE_TYPECINEMATIQUE, typeCinematique);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_TYPECINEMATIQUE, deleteVo);
  }

  public update(typeCinematique: TypeCinematique) {
    return this.http.post(UPDATE_TYPECINEMATIQUE, typeCinematique);
  }

  public findByCode(codeTypeCinematique: string): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_TYPECINEMATIQUE_BY_CODE + codeTypeCinematique);
  }
  
}
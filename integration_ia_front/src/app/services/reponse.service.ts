import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DeleteVo } from 'app/class/DeleteVo';
import { Reponse } from 'app/class/Reponse';
import { DELETE_REPONSE, FIND_ALL_REPONSE, FIND_REPONSE_BY_CODE, SAVE_REPONSE, UPDATE_REPONSE } from 'app/helpers/url.constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReponseService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_ALL_REPONSE);
  }

  public save(reponse: Reponse) {
    return this.http.post(SAVE_REPONSE, reponse);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_REPONSE, deleteVo);
  }

  public update(reponse: Reponse) {
    return this.http.post(UPDATE_REPONSE, reponse);
  }

  public findByCode(codeReponse: string): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_REPONSE_BY_CODE + codeReponse);
  }
  
}



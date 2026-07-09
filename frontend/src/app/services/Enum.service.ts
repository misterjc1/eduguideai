import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EnumVo } from '../class/EnumVo';
import { MTResponse } from '../class/MTResponse';
import { FIND_ALL_NIVEAU, FIND_ALL_STATUT_LIAISON, FIND_ALL_TYPE_UTILISATEUR, FIND_ALL_TYPESERVICES, FIND_ALL_VARIABLES } from '../helpers/url.constants';

@Injectable({
  providedIn: 'root'
})
export class EnumService {

  constructor(private http: HttpClient) { }

  public findAllNiveau(): Observable<MTResponse<EnumVo[]>> {
    return this.http.get<MTResponse<EnumVo[]>>(FIND_ALL_NIVEAU);
  }

  public findAllVariables(): Observable<MTResponse<EnumVo[]>> {
    return this.http.get<MTResponse<EnumVo[]>>(FIND_ALL_VARIABLES);
  }

  public findAllTypeServices(): Observable<MTResponse<EnumVo[]>> {
    return this.http.get<MTResponse<EnumVo[]>>(FIND_ALL_TYPESERVICES);
  }

  public findAllStatutLiaison(): Observable<MTResponse<EnumVo[]>> {
    return this.http.get<MTResponse<EnumVo[]>>(FIND_ALL_STATUT_LIAISON);
  }

  public findAllTypeUtilisateur(): Observable<MTResponse<EnumVo[]>> {
    return this.http.get<MTResponse<EnumVo[]>>(FIND_ALL_TYPE_UTILISATEUR);
  }

}

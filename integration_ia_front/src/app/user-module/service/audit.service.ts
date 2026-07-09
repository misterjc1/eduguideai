import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuditVo } from '../class/audit-vo';
import { MTResponse } from '../class/MTResponse';
import { FIND_ALL_AUDIT } from '../helpers/url.constants';

@Injectable({
  providedIn: 'root'
})
export class AuditService {

  constructor(private http: HttpClient) { }

  public findAll (): Observable<MTResponse<AuditVo[]>> {
    return this.http.get<MTResponse<AuditVo[]>>(FIND_ALL_AUDIT);
  }



}

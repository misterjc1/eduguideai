import { FIND_SESSION_BY_CODE, UPDATE_SESSION, FIND_SESSION_BY_USER } from './../helpers/url.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Response } from 'app/class/Response';
import { Observable } from 'rxjs';
import { Session } from '../class/Session';
import { FIND_ALL_SESSION, SAVE_SESSION } from '../helpers/url.constants';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_ALL_SESSION);
  }

  public save(session: Session) {
    return this.http.post(SAVE_SESSION, session);
  }

  public update(session: Session) {
    return this.http.post(UPDATE_SESSION, session);
  }

  public findByCode(code): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_SESSION_BY_CODE + code);
  }

  public findByUser(code: string): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_SESSION_BY_USER + code);
  }
}

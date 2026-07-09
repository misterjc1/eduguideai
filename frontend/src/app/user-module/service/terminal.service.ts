import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Response } from 'app/class/Response';
import { Observable } from 'rxjs';
import { FIND_TERMINAL_BY_CODE } from '../helpers/url.constants';

@Injectable({
  providedIn: 'root'
})
export class TerminalService {

  constructor(private http: HttpClient) { }

  public findByCode(codeTerminal): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_TERMINAL_BY_CODE + codeTerminal);
  }


}

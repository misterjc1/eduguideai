import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DeleteVo } from 'app/class/DeleteVo';
import { Observable } from 'rxjs';
import { DELETE_PROMPT, FIND_ALL_PROMPT, FIND_PROMPT_BY_CODE, SAVE_PROMPT, UPDATE_PROMPT } from 'app/helpers/url.constants';
import { Prompt } from 'app/class/Prompt';


@Injectable({
  providedIn: 'root'
})
export class PromptService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_ALL_PROMPT);
  }

  public save(prompt: Prompt) {
    return this.http.post(SAVE_PROMPT, prompt);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_PROMPT, deleteVo);
  }

  public update(prompt: Prompt) {
    return this.http.post(UPDATE_PROMPT, prompt);
  }

  public findByCode(codePrompt: string): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_PROMPT_BY_CODE + codePrompt);
  }
  
}


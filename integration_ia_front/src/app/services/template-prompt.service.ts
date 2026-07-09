import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DeleteVo } from 'app/class/DeleteVo';
import { TemplatePrompt } from 'app/class/TemplatePrompt';
import { DELETE_TEMPLATEPROMPT, FIND_ALL_TEMPLATEPROMPT, FIND_TEMPLATEPROMPT_BY_CODE, SAVE_TEMPLATEPROMPT, UPDATE_TEMPLATEPROMPT } from 'app/helpers/url.constants';
import { Observable, map, catchError, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TemplatePromptService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_ALL_TEMPLATEPROMPT);
  }

  public save(templateTemplatePrompt: TemplatePrompt) {
    return this.http.post(SAVE_TEMPLATEPROMPT, templateTemplatePrompt);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_TEMPLATEPROMPT, deleteVo);
  }

  public update(templateTemplatePrompt: TemplatePrompt) {
    return this.http.post(UPDATE_TEMPLATEPROMPT, templateTemplatePrompt);
  }

  /*public findByCode(codeTemplatePrompt): Observable<Response[]> {
    return this.http.get<Response[]>(FIND_TEMPLATEPROMPT_BY_CODE + codeTemplatePrompt);
  }*/

    /*public findByCode(codeTemplatePrompt: string): Observable<string> {
      return this.http.get(FIND_TEMPLATEPROMPT_BY_CODE + codeTemplatePrompt, { responseType: 'text' });
    }*/

      public findByCode(codeTemplatePrompt: string): Observable<TemplatePrompt> {
        return this.http.get<TemplatePrompt>(FIND_TEMPLATEPROMPT_BY_CODE + codeTemplatePrompt);
      }
      
      
  
}

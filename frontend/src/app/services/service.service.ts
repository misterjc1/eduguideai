import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DeleteVo } from 'app/class/DeleteVo';
import { Service } from 'app/class/Service';
import { AFFICHER_SERVICES_LOGO, DELETE_SERVICES, FIND_ALL_SERVICES, FIND_SERVICES_BY_CODE, FIND_SERVICES_LOGO, SAVE_SERVICES, UPDATE_SERVICES } from 'app/helpers/url.constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {


  constructor(private http: HttpClient) { }

  public findAll(): Observable<Service[]> {
    return this.http.get<Service[]>(FIND_ALL_SERVICES);
  }

  public save(service: Service) {
    return this.http.post(SAVE_SERVICES, service);
  }

  public delete(deleteVo: DeleteVo) {
    return this.http.post(DELETE_SERVICES, deleteVo);
  }

  public update(service: Service) {
    return this.http.post(UPDATE_SERVICES, service);
  }

  public findByCode(codeService: string): Observable<Service[]> {
    return this.http.get<Service[]>(FIND_SERVICES_BY_CODE + codeService);
  }

  public findLogo(imageUrl: string) {
    return this.http.get(FIND_SERVICES_LOGO + imageUrl);
  }

  // public findLogo(codeService): Observable<Response[]> {
  //   return this.http.get<Response[]>(AFFICHER_SERVICES_LOGO + codeService);
  // }
  
}


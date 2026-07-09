import { TestBed } from '@angular/core/testing';

import { UtilisateurClassService } from './utilisateur-class.service';

describe('UtilisateurClassService', () => {
  let service: UtilisateurClassService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UtilisateurClassService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

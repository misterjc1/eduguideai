import { TestBed } from '@angular/core/testing';

import { TypeCinematiqueService } from './type-cinematique.service';

describe('TypeCinematiqueService', () => {
  let service: TypeCinematiqueService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TypeCinematiqueService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

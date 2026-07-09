import { TestBed } from '@angular/core/testing';

import { CinematiqueService } from './cinematique.service';

describe('CinematiqueService', () => {
  let service: CinematiqueService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CinematiqueService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { InscritService } from './inscrit.service';

describe('InscritService', () => {
  let service: InscritService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InscritService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

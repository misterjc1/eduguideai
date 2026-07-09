import { TestBed } from '@angular/core/testing';

import { InscritListService } from './inscrit-list.service';

describe('InscritListService', () => {
  let service: InscritListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InscritListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

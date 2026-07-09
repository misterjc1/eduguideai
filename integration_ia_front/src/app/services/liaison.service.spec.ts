import { TestBed } from '@angular/core/testing';

import { LiaisonService } from './liaison.service';

describe('LiaisonService', () => {
  let service: LiaisonService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LiaisonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

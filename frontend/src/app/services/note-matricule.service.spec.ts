import { TestBed } from '@angular/core/testing';

import { NoteMatriculeService } from './note-matricule.service';

describe('NoteMatriculeService', () => {
  let service: NoteMatriculeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NoteMatriculeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

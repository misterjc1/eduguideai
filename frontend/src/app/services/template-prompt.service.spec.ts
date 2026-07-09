import { TestBed } from '@angular/core/testing';

import { TemplatePromptService } from './template-prompt.service';

describe('TemplatePromptService', () => {
  let service: TemplatePromptService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TemplatePromptService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

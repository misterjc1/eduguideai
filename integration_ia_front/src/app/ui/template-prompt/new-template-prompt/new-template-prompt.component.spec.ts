import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewTemplatePromptComponent } from './new-template-prompt.component';

describe('NewTemplatePromptComponent', () => {
  let component: NewTemplatePromptComponent;
  let fixture: ComponentFixture<NewTemplatePromptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewTemplatePromptComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewTemplatePromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

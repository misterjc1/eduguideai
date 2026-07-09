import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewPromptComponent } from './new-prompt.component';

describe('NewPromptComponent', () => {
  let component: NewPromptComponent;
  let fixture: ComponentFixture<NewPromptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewPromptComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewPromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

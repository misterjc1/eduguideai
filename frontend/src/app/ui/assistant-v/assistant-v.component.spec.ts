import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssistantVComponent } from './assistant-v.component';

describe('AssistantVComponent', () => {
  let component: AssistantVComponent;
  let fixture: ComponentFixture<AssistantVComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssistantVComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssistantVComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

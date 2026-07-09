import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewAssistantVirtuelComponent } from './new-assistant-virtuel.component';

describe('NewAssistantVirtuelComponent', () => {
  let component: NewAssistantVirtuelComponent;
  let fixture: ComponentFixture<NewAssistantVirtuelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewAssistantVirtuelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewAssistantVirtuelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

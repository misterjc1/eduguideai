import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewAnalysePredictiveComponent } from './new-analyse-predictive.component';

describe('NewAnalysePredictiveComponent', () => {
  let component: NewAnalysePredictiveComponent;
  let fixture: ComponentFixture<NewAnalysePredictiveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewAnalysePredictiveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewAnalysePredictiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewDetectionFraudComponent } from './new-detection-fraud.component';

describe('NewDetectionFraudComponent', () => {
  let component: NewDetectionFraudComponent;
  let fixture: ComponentFixture<NewDetectionFraudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewDetectionFraudComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewDetectionFraudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

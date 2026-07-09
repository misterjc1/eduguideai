import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetectionFraudComponent } from './detection-fraud.component';

describe('DetectionFraudComponent', () => {
  let component: DetectionFraudComponent;
  let fixture: ComponentFixture<DetectionFraudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetectionFraudComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetectionFraudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

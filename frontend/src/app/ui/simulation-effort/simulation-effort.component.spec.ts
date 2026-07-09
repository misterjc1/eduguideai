import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SimulationEffortComponent } from './simulation-effort.component';

describe('SimulationEffortComponent', () => {
  let component: SimulationEffortComponent;
  let fixture: ComponentFixture<SimulationEffortComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SimulationEffortComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SimulationEffortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

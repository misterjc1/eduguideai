import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewSimulationEffortComponent } from './new-simulation-effort.component';

describe('NewSimulationEffortComponent', () => {
  let component: NewSimulationEffortComponent;
  let fixture: ComponentFixture<NewSimulationEffortComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewSimulationEffortComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewSimulationEffortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalysePredComponent } from './analyse-pred.component';

describe('AnalysePredComponent', () => {
  let component: AnalysePredComponent;
  let fixture: ComponentFixture<AnalysePredComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnalysePredComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalysePredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

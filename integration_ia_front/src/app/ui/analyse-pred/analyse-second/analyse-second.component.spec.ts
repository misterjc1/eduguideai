import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyseSecondComponent } from './analyse-second.component';

describe('AnalyseSecondComponent', () => {
  let component: AnalyseSecondComponent;
  let fixture: ComponentFixture<AnalyseSecondComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnalyseSecondComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyseSecondComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

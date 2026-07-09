import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyseThirdComponent } from './analyse-third.component';

describe('AnalyseThirdComponent', () => {
  let component: AnalyseThirdComponent;
  let fixture: ComponentFixture<AnalyseThirdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnalyseThirdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyseThirdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

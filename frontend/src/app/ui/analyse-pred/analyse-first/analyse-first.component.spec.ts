import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyseFirstComponent } from './analyse-first.component';

describe('AnalyseFirstComponent', () => {
  let component: AnalyseFirstComponent;
  let fixture: ComponentFixture<AnalyseFirstComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnalyseFirstComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyseFirstComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

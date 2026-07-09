import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewInscritComponent } from './new-inscrit.component';

describe('NewInscritComponent', () => {
  let component: NewInscritComponent;
  let fixture: ComponentFixture<NewInscritComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewInscritComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewInscritComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

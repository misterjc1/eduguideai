import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeuxiemeNiveauComponent } from './deuxieme-niveau.component';

describe('DeuxiemeNiveauComponent', () => {
  let component: DeuxiemeNiveauComponent;
  let fixture: ComponentFixture<DeuxiemeNiveauComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeuxiemeNiveauComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeuxiemeNiveauComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

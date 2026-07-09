import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TroisiemeNiveauComponent } from './troisieme-niveau.component';

describe('TroisiemeNiveauComponent', () => {
  let component: TroisiemeNiveauComponent;
  let fixture: ComponentFixture<TroisiemeNiveauComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TroisiemeNiveauComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TroisiemeNiveauComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

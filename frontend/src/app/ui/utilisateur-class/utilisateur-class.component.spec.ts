import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UtilisateurClassComponent } from './utilisateur-class.component';

describe('UtilisateurClassComponent', () => {
  let component: UtilisateurClassComponent;
  let fixture: ComponentFixture<UtilisateurClassComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UtilisateurClassComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UtilisateurClassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

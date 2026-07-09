import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewUtilisateurClassComponent } from './new-utilisateur-class.component';

describe('NewUtilisateurClassComponent', () => {
  let component: NewUtilisateurClassComponent;
  let fixture: ComponentFixture<NewUtilisateurClassComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewUtilisateurClassComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewUtilisateurClassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

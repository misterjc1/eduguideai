import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObjectifSecondaireComponent } from './objectif-secondaire.component';

describe('ObjectifSecondaireComponent', () => {
  let component: ObjectifSecondaireComponent;
  let fixture: ComponentFixture<ObjectifSecondaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ObjectifSecondaireComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ObjectifSecondaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

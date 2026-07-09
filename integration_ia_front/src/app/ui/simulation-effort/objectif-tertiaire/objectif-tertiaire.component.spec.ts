import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObjectifTertiaireComponent } from './objectif-tertiaire.component';

describe('ObjectifTertiaireComponent', () => {
  let component: ObjectifTertiaireComponent;
  let fixture: ComponentFixture<ObjectifTertiaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ObjectifTertiaireComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ObjectifTertiaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewDonneesComponent } from './new-donnees.component';

describe('NewDonneesComponent', () => {
  let component: NewDonneesComponent;
  let fixture: ComponentFixture<NewDonneesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewDonneesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewDonneesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InscritTuteurComponent } from './inscrit-tuteur.component';

describe('InscritTuteurComponent', () => {
  let component: InscritTuteurComponent;
  let fixture: ComponentFixture<InscritTuteurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InscritTuteurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InscritTuteurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

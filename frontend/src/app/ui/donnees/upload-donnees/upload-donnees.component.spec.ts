import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadDonneesComponent } from './upload-donnees.component';

describe('UploadDonneesComponent', () => {
  let component: UploadDonneesComponent;
  let fixture: ComponentFixture<UploadDonneesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadDonneesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadDonneesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

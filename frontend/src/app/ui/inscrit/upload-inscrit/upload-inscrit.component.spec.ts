import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadInscritComponent } from './upload-inscrit.component';

describe('UploadInscritComponent', () => {
  let component: UploadInscritComponent;
  let fixture: ComponentFixture<UploadInscritComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadInscritComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadInscritComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadNiveauComponent } from './upload-niveau.component';

describe('UploadNiveauComponent', () => {
  let component: UploadNiveauComponent;
  let fixture: ComponentFixture<UploadNiveauComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadNiveauComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadNiveauComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

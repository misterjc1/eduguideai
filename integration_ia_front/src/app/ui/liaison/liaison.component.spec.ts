import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LiaisonComponent } from './liaison.component';

describe('LiaisonComponent', () => {
  let component: LiaisonComponent;
  let fixture: ComponentFixture<LiaisonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LiaisonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LiaisonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

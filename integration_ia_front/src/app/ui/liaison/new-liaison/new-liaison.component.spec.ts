import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewLiaisonComponent } from './new-liaison.component';

describe('NewLiaisonComponent', () => {
  let component: NewLiaisonComponent;
  let fixture: ComponentFixture<NewLiaisonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewLiaisonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewLiaisonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

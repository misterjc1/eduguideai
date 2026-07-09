import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewParamsComponent } from './new-params.component';

describe('NewParamsComponent', () => {
  let component: NewParamsComponent;
  let fixture: ComponentFixture<NewParamsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewParamsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewParamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

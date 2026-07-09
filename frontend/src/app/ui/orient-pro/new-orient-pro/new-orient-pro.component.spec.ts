import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewOrientProComponent } from './new-orient-pro.component';

describe('NewOrientProComponent', () => {
  let component: NewOrientProComponent;
  let fixture: ComponentFixture<NewOrientProComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewOrientProComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewOrientProComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

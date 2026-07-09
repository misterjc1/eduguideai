import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrientProComponent } from './orient-pro.component';

describe('OrientProComponent', () => {
  let component: OrientProComponent;
  let fixture: ComponentFixture<OrientProComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrientProComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrientProComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

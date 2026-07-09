import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewCinematiqueComponent } from './new-cinematique.component';

describe('NewCinematiqueComponent', () => {
  let component: NewCinematiqueComponent;
  let fixture: ComponentFixture<NewCinematiqueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewCinematiqueComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewCinematiqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

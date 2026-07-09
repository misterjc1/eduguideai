import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewTypeCinematiqueComponent } from './new-type-cinematique.component';

describe('NewTypeCinematiqueComponent', () => {
  let component: NewTypeCinematiqueComponent;
  let fixture: ComponentFixture<NewTypeCinematiqueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewTypeCinematiqueComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewTypeCinematiqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

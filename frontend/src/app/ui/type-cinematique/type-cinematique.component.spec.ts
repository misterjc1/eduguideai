import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeCinematiqueComponent } from './type-cinematique.component';

describe('TypeCinematiqueComponent', () => {
  let component: TypeCinematiqueComponent;
  let fixture: ComponentFixture<TypeCinematiqueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TypeCinematiqueComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TypeCinematiqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UseParametreComponent } from './use-parametre.component';

describe('UseParametreComponent', () => {
  let component: UseParametreComponent;
  let fixture: ComponentFixture<UseParametreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UseParametreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UseParametreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

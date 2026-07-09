import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LiaisonDetailsComponent } from './liaison-details.component';

describe('LiaisonDetailsComponent', () => {
  let component: LiaisonDetailsComponent;
  let fixture: ComponentFixture<LiaisonDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LiaisonDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LiaisonDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

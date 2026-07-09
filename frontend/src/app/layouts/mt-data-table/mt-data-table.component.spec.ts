import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MtDataTableComponent } from './mt-data-table.component';

describe('MtDataTableComponent', () => {
  let component: MtDataTableComponent;
  let fixture: ComponentFixture<MtDataTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MtDataTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MtDataTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

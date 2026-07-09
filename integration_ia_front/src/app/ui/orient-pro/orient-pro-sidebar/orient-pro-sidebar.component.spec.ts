import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrientProSidebarComponent } from './orient-pro-sidebar.component';

describe('OrientProSidebarComponent', () => {
  let component: OrientProSidebarComponent;
  let fixture: ComponentFixture<OrientProSidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrientProSidebarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrientProSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

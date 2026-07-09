import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewReponseComponent } from './new-reponse.component';

describe('NewReponseComponent', () => {
  let component: NewReponseComponent;
  let fixture: ComponentFixture<NewReponseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewReponseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewReponseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArrivalPopupComponent } from './arrival-popup.component';

describe('ArrivalPopupComponent', () => {
  let component: ArrivalPopupComponent;
  let fixture: ComponentFixture<ArrivalPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArrivalPopupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArrivalPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

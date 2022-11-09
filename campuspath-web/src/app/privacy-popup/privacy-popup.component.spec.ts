import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrivacyPopupComponent } from './privacy-popup.component';

describe('PrivacyPopupComponent', () => {
  let component: PrivacyPopupComponent;
  let fixture: ComponentFixture<PrivacyPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PrivacyPopupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrivacyPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

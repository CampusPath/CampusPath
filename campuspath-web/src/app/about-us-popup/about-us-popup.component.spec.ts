import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutUsPopupComponent } from './about-us-popup.component';

describe('AboutUsPopupComponent', () => {
  let component: AboutUsPopupComponent;
  let fixture: ComponentFixture<AboutUsPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AboutUsPopupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AboutUsPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

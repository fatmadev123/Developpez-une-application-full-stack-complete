import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BackArrowLinkComponent } from './back-arrow-link.component';

describe('BackArrowLinkComponent', () => {
  let component: BackArrowLinkComponent;
  let fixture: ComponentFixture<BackArrowLinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BackArrowLinkComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BackArrowLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

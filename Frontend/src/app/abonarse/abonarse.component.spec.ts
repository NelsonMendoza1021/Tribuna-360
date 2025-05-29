import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbonarseComponent } from './abonarse.component';

describe('AbonarseComponent', () => {
  let component: AbonarseComponent;
  let fixture: ComponentFixture<AbonarseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AbonarseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AbonarseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

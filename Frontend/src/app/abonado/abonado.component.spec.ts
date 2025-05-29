import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbonadoComponent } from './abonado.component';

describe('AbonadoComponent', () => {
  let component: AbonadoComponent;
  let fixture: ComponentFixture<AbonadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AbonadoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AbonadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

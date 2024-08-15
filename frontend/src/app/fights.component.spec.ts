// frontend/src/app/fight.components.specs.ts

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FightsComponent } from './fights.component';

describe('FightsComponent', () => {
  let component: FightsComponent;
  let fixture: ComponentFixture<FightsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FightsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FightsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

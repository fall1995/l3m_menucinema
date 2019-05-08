import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdresseUpdateDialogComponent } from './adresse-update-dialog.component';

describe('AdresseUpdateDialogComponent', () => {
  let component: AdresseUpdateDialogComponent;
  let fixture: ComponentFixture<AdresseUpdateDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdresseUpdateDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdresseUpdateDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

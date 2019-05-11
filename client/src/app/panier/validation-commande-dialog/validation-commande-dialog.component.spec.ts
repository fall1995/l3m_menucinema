import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidationCommandeDialogComponent } from './validation-commande-dialog.component';

describe('ValidationCommandeDialogComponent', () => {
  let component: ValidationCommandeDialogComponent;
  let fixture: ComponentFixture<ValidationCommandeDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ValidationCommandeDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ValidationCommandeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

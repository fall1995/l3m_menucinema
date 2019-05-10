import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmsPlusCommandeComponent } from './films-plus-commande.component';

describe('FilmsPlusCommandeComponent', () => {
  let component: FilmsPlusCommandeComponent;
  let fixture: ComponentFixture<FilmsPlusCommandeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilmsPlusCommandeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilmsPlusCommandeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

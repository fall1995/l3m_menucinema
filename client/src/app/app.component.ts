import { Component } from '@angular/core';
import {TmdbService} from './service/tmdb.service';
import {MovieResponse} from './tmdb-data/Movie';
import {environment} from '../environments/environment';
import {error} from 'util';

// Je suis passé par l'itération 0...
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  pFilm: MovieResponse;
  listeMovie: MovieResponse;

  constructor(private tmdb: TmdbService) {
    this.init();
  }

  async init() {
    this.tmdb.init( environment.tmdbKey );
    this.pFilm = await this.tmdb.getMovie(14);
    this.pFilm = await this.tmdb.getAllMovie();
  }


  get film(): MovieResponse {
    return this.pFilm;
  }

}

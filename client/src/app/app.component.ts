import { Component } from '@angular/core';
import {TmdbService} from './tmdb.service';
import {MovieResponse} from './tmdb-data/Movie';
import {environment} from '../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  pFilm: MovieResponse;

  constructor(private tmdb: TmdbService) {
    this.init();
  }

  async init() {
    this.tmdb.init( environment.tmdbKey );
    this.pFilm = await this.tmdb.getMovie(14);
  }

  get film(): MovieResponse {
    return this.pFilm;
  }

}

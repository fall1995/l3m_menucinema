import { Component, OnInit } from '@angular/core';
import {environment} from '../../environments/environment';
import {MovieResponse} from '../tmdb-data/Movie';
import {TmdbService} from '../service/tmdb.service';

@Component({
  selector: 'app-films',
  templateUrl: './films.component.html',
  styleUrls: ['./films.component.scss']
})
export class FilmsComponent implements OnInit {

    idFilm: MovieResponse;
    listeMovie: MovieResponse;

  constructor(private tmdb: TmdbService) {
      this.init();
  }

  ngOnInit() {
  }
    async init() {
        this.tmdb.init( environment.tmdbKey );
        this.idFilm = await this.tmdb.getMovie(14);
        this.listeMovie = await this.tmdb.getAllMovie();
    }

    get film(): MovieResponse {
        return this.idFilm;
    }

}

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

  constructor(private tmdbService: TmdbService) {

  }

  ngOnInit() {
      this.init();
  }
    /*
    async getMovies(){
      this.tmdbService.getAllMovie().then(
          data =>{
              this.films = data;
          }, error => {
              console.log(error);
      }
      );
  }
  */
    async init() {
        this.tmdbService.init( environment.tmdbKey );
        this.idFilm = await this.tmdbService.getMovie(14);
        this.tmdbService.getAllMovie().then(
            data =>{
                this.listeMovie = data;
            }, error =>{
                console.log(error);
            }
        );
    }

    get film(): MovieResponse {
        return this.listeMovie;
    }
    getPhotoURL() {
        return "https://api.themoviedb.org/3"+this.listeMovie.results;
    }

}

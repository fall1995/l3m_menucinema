import { Component, OnInit } from '@angular/core';
import {environment} from '../../environments/environment';
import {MovieResponse} from '../tmdb-data/Movie';
import {TmdbService} from '../service/tmdb.service';
import {TVResponse} from '../tmdb-data/TV';
import { ShowOnDirtyErrorStateMatcher } from '@angular/material';

@Component({
  selector: 'app-films',
  templateUrl: './films.component.html',
  styleUrls: ['./films.component.scss']
})
export class FilmsComponent implements OnInit {

    idFilm: MovieResponse;
    listeMovie: MovieResponse;
    listeMovieDram : MovieResponse;
    listeMovieHightRatet : MovieResponse;
    afficherDialog = false;
    detail_afficher='Detail';
    detail_cacher='Afficher detail';

    detail:string= this.detail_afficher;

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
 afficher():void{
     if(this.detail=this.detail_cacher){

         this.detail=this.detail_afficher;
     }
     else{
        this.detail=this.detail_cacher;
     }

 }
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
        this.listeMovieDram = await this.tmdbService.getAllDrama();
        this.listeMovieHightRatet = await this.tmdbService.getAllHightRated();
    }


}

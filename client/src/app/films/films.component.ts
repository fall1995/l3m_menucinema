import { Component, OnInit } from '@angular/core';
import {environment} from '../../environments/environment';
import {MovieResponse} from '../tmdb-data/Movie';
import {TmdbService} from '../service/tmdb.service';
import {StorageService} from '../service/storage.service';
import {MessageService} from 'primeng/api';
import {Plats} from '../menu-commade-data/Menu';
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
    selectedMovie: MovieResponse; // movie selectionner
    displayDialog: boolean; // Dialog pour les movie

    detail:string= this.detail_afficher;

  constructor(private tmdbService: TmdbService, private storage: StorageService,
                private message: MessageService) { }
  ngOnInit() {
      this.init();
  }


    /**
     * méthode d'ajout dans le panier
     * @param id
     */
    addFilmNote(id: number) {
        let film = this.listeMovieHightRatet.results.find(films => films.id === id);
        this.storage.addMovieNote(film);
        this.message.add({severity:'success',
            summary:`Ajout du Film ${film.title} `,
            detail:'Votre choix a bien été ajouter dans votre panier'});

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

    selectMovie(movie: MovieResponse) {
        this.selectedMovie = movie;
        this.displayDialog = true;
        event.preventDefault();
    }
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

import { Component, OnInit } from '@angular/core';
import {TmdbService} from '../service/tmdb.service';
import {StorageService} from '../service/storage.service';
import {environment} from '../../environments/environment';
import {MovieResponse} from '../tmdb-data/Movie';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-films-plus-commande',
  templateUrl: './films-plus-commande.component.html',
  styleUrls: ['./films-plus-commande.component.scss']
})
export class FilmsPlusCommandeComponent implements OnInit {
  idFilm: MovieResponse;
    listeMovie: MovieResponse;
    listeMovieDram : MovieResponse;
    listeMovieHightRatet : MovieResponse;
    afficherDialog = false;
    detail_afficher='Detail';
    detail_cacher='Afficher detail';
    selectedMovie: MovieResponse; // movie selectionner
    displayDialog: boolean; // Dialog pour les movie
    movie: any[];
    idPlat: string;

  constructor(private tmdbService: TmdbService, private storage: StorageService,
    private message: MessageService) { }

  ngOnInit() {
    this.init();
  }
    /**
     * je recupere ici les films les plus commande avec un donne
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
      this.listeMovieDram = await this.tmdbService.getAllDrama();
      this.listeMovieHightRatet = await this.tmdbService.getAllHightRated();
  }


}

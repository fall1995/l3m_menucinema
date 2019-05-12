import { Component, OnInit } from '@angular/core';
import {environment} from '../../environments/environment';
import {MovieResponse} from '../tmdb-data/Movie';
import {TmdbService} from '../service/tmdb.service';
import {StorageService} from '../service/storage.service';
import {MessageService} from 'primeng/api';
@Component({
  selector: 'app-films',
  templateUrl: './films.component.html',
  styleUrls: ['./films.component.scss']
})
export class FilmsComponent implements OnInit {

    listeMovie: MovieResponse; // movie de 2019
    listeMovieDram : MovieResponse; // movie les dramatique
    listeMovieHightRatet : MovieResponse; // movie les mieux notés
    selectedMovie: MovieResponse; // movie selectionner
    displayDialog: boolean; // Dialog pour les movie


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

    /**
     * recuperation des movies
     */
    async init() {
        this.tmdbService.init( environment.tmdbKey );
        this.listeMovie = await  this.tmdbService.getAllMovie();
        this.listeMovieDram = await this.tmdbService.getAllDrama();
        this.listeMovieHightRatet = await this.tmdbService.getAllHightRated();
    }

    /**
     * ajout des films dramatiques dans le panier
     * @param id
     */
    ajoutFilmDramatique(id: number){
        let filmdrama = this.listeMovieDram.results.find(film => film.id === id);
        this.storage.addMovieNote(filmdrama);
        this.message.add({severity:'success',
            summary:`Ajout du Film ${filmdrama.title} `,
            detail:'Votre choix a bien été ajouter dans votre panier'});
    }

    /**
     * reference vers  le film choisi pour voir le detail
     * @param movie
     */
    selectMovie(movie: MovieResponse) {
        this.selectedMovie = movie;
        this.displayDialog = true;
    }

    /**
     * ajout des films recent dans le panier
     * @param id
     */
    ajoutFilmRecent(id: number){
        let film2019 = this.listeMovie.results.find(film => film.id === id);
        this.storage.addMovieNote(film2019);
        this.message.add({severity:'success',
            summary:`Ajout du Film ${film2019.title} `,
            detail:'Votre choix a bien été ajouter dans votre panier'});
    }


}

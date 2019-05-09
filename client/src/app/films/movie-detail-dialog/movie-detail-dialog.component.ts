import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MovieResponse} from '../../tmdb-data/Movie';
import {TmdbService} from '../../service/tmdb.service';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-movie-detail-dialog',
  templateUrl: './movie-detail-dialog.component.html',
  styleUrls: ['./movie-detail-dialog.component.scss']
})
export class MovieDetailDialogComponent implements OnInit {

  constructor(private tmdbService: TmdbService) { }

    idFilm: MovieResponse;
    listeMovie: MovieResponse;
    listeMovieDram: MovieResponse;
    listeMovieHightRatet: MovieResponse;

  ngOnInit() {
    this.init();
  }


    @Input() film: MovieResponse;
    /**
     * boolean pour afficher le dialog
     */
    @Input() afficherDialog = false;

    /**
     * evenement après la fermeture du dialog
     */
    @Output() onDialogHide = new EventEmitter(true);

    /**
     * Méthode appelée lors de la fermeture du dialogue
     */
    onHide(): void {
        this.onDialogHide.emit();
    }

    /**
     * Méthode appelée lors de l'ouverture du dialogue
     */
    onShow(): void {
    }

    async init() {
        this.tmdbService.init(environment.tmdbKey);
        this.idFilm = await this.tmdbService.getMovie(14);
        this.tmdbService.getAllMovie().then(
            data => {
                this.listeMovie = data;
            }, error => {
                console.log(error);
            }
        );
        this.listeMovieDram = await this.tmdbService.getAllDrama();
        this.listeMovieHightRatet = await this.tmdbService.getAllHightRated();
    }

}

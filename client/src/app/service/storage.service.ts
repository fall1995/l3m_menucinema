import {Injectable} from '@angular/core';
import {Plats} from '../menu-commade-data/Menu';
import {MovieResponse} from '../tmdb-data/Movie';

@Injectable({
    providedIn: 'root'
})
export class StorageService {
    constructor() {
    }

    /**
     * ajout des plat le localStorage avant l'utilisation
     * @param plat
     */
    addMenu(plat: Plats) {
        let dataPrix: number[] = [];
        let dataMenuId: string[] = [];
        if (localStorage.getItem('plat') == null) {
            let data: Plats[] = [];
            data.push(plat);
            for (let p of data) {
                dataMenuId.push(p.id);
                dataPrix.push(p.prix);
            }
            localStorage.setItem('totalMenu', JSON.stringify(dataPrix)); // modification dans le storage
            localStorage.setItem('platId', JSON.stringify(dataMenuId)); //
            localStorage.setItem('plat', JSON.stringify(data));
        } else {
            let data: Plats[] = JSON.parse(localStorage.getItem('plat'));
            data.push(plat);
            for (let p of data) {
                dataMenuId.push(p.id);
                dataPrix.push(p.prix);
            }
            localStorage.setItem('totalMenu', JSON.stringify(dataPrix));
            localStorage.setItem('platId', JSON.stringify(dataMenuId));
            localStorage.setItem('plat', JSON.stringify(data));
        }
    }

    /**
     * ajout des films dans le localStorage
     * @param film
     */
    addMovieNote(film: MovieResponse) {
        let dataTotal: number[] = [];
        let dataMovieId: string[] = [];
        if (localStorage.getItem('filmNote') == null) {
            let data: MovieResponse[] = [];
            data.push(film);
            for (let m of data) {
                dataMovieId.push(m.title);
                dataTotal.push(3.79);
            }
            localStorage.setItem('totalMovie', JSON.stringify(dataTotal));
            localStorage.setItem('movieId', JSON.stringify(dataMovieId));
            localStorage.setItem('filmNote', JSON.stringify(data));
        } else {
            let data: MovieResponse[] = JSON.parse(localStorage.getItem('filmNote'));
            data.push(film);
            for (let m of data) {
                dataMovieId.push(m.title);
                dataTotal.push(3.79);
            }
            localStorage.setItem('totalMovie', JSON.stringify(dataTotal));
            localStorage.setItem('movieId', JSON.stringify(dataMovieId));
            localStorage.setItem('filmNote', JSON.stringify(data));
        }
    }

    /**
     * recuperation des films les mieux noté
     */
    getMieuNote(): MovieResponse[] {
        if (localStorage.getItem('filmNote') != null) {
            return JSON.parse(localStorage.getItem('filmNote'));
        }
        return null;
    }

    /**
     * recuperation des menus pour l'afficher dans le panier
     */
    getMenu(): Plats[] {
        if (localStorage.getItem('plat') != null) {
            return JSON.parse(localStorage.getItem('plat'));

        }
        return null;
    }

    /**
     * effacer le plat dont l'index est passé en parametre
     * @param index
     */
    delete(index: number): void {
        let dataPrix: number[] = JSON.parse(localStorage.getItem('totalMenu'));
        let dataMenuId: string[] = JSON.parse(localStorage.getItem('platId')); // je supprime aussi l'id ajouter lors de l'insertion
        let data: Plats[] = JSON.parse(localStorage.getItem('plat'));
        data.splice(index, 1);
        dataMenuId.splice(index, 1);
        dataPrix.splice(index, 1);
        //total--;
        localStorage.setItem('plat', JSON.stringify(data));
        localStorage.setItem('platId', JSON.stringify(dataMenuId));
        localStorage.setItem('totalMenu', JSON.stringify(dataPrix));
    }

    /**
     * effacer un film dont l'index est passer en parametre
     * @param index
     */
    deleteMovie(index: number): void {
        let dataTotal: number[] = JSON.parse(localStorage.getItem('totalMovie'));
        let dataMovieId: number[] = JSON.parse(localStorage.getItem('movieId')); // je supprime aussi l'id du movie ajouter
        let data: MovieResponse[] = JSON.parse(localStorage.getItem('filmNote'));
        data.splice(index, 1);
        dataMovieId.splice(index, 1);
        dataTotal.splice(index, 1);
        localStorage.setItem('filmNote', JSON.stringify(data));
        localStorage.setItem('movieId', JSON.stringify(dataMovieId));
        localStorage.setItem('totalMovie', JSON.stringify(dataTotal));
    }

}

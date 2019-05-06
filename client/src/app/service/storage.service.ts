import {Injectable} from '@angular/core';
import {ListePlats} from '../menu-commade-data/Menu';
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
    addMenu(plat: ListePlats) {
        let dataMenuId: string[] = [];
        if (localStorage.getItem('plat') == null) {
            let data: ListePlats[] = [];
            data.push(plat);
            for (let p of data){
                dataMenuId.push(p.id);
            }
            localStorage.setItem('platId', JSON.stringify(dataMenuId));
            localStorage.setItem('plat', JSON.stringify(data));
        } else {
            let data: ListePlats[] = JSON.parse(localStorage.getItem('plat'));
            data.push(plat);
            for (let p of data){
                dataMenuId.push(p.id);
            }
            localStorage.setItem('platId', JSON.stringify(dataMenuId));
            localStorage.setItem('plat', JSON.stringify(data));
        }
    }

    /**
     * ajout des films dans le localStorage
     * @param film
     */
    addMovieNote(film: MovieResponse) {
        let dataMovieId: number[] = [];
        if (localStorage.getItem('filmNote') == null) {
            let data: MovieResponse[] = [];
            data.push(film);
            for (let m of data){
                dataMovieId.push(m.id);
            }
            localStorage.setItem('movieId', JSON.stringify(dataMovieId));
            localStorage.setItem('filmNote', JSON.stringify(data));
        } else {
            let data: MovieResponse[] = JSON.parse(localStorage.getItem('filmNote'));
            data.push(film);
            for (let m of data){
                dataMovieId.push(m.id);
            }
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
    getMenu(): ListePlats[] {
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
        let data: ListePlats[] = JSON.parse(localStorage.getItem('plat'));
        data.splice(index, 1);
        localStorage.setItem('plat', JSON.stringify(data));
    }

    /**
     * effacer un film dont l'index est passer en parametre
     * @param index
     */
    deleteMovie(index: number): void {
        let data: MovieResponse[] = JSON.parse(localStorage.getItem('filmNote'));
        data.splice(index, 1);
        localStorage.setItem('filmNote', JSON.stringify(data));

    }

}

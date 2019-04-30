import {Injectable} from '@angular/core';
import {MovieQuery, MovieResponse} from '../tmdb-data/Movie';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {PersonQuery, PersonResponse} from '../tmdb-data/Person';
import {SearchMovieQuery, SearchMovieResponse} from '../tmdb-data/searchMovie';
import {SearchPeopleQuery, SearchPeopleResponse} from '../tmdb-data/SearchPeople';
import {TVQuery, TVResponse} from '../tmdb-data/TV';
import {SearchTVQuery, SearchTVResponse} from '../tmdb-data/SearchTV';
import {Observable} from 'rxjs';

const tmdbApi = 'https://api.themoviedb.org/3';
type HTTP_METHOD = 'GET' | 'POST' | 'DELETE' | 'PUT';

function AlxToObjectString(data?: object): { [key: string]: string } {
    const res = {};
    for (const k of Object.keys(data || {})) {
        const v = data[k];
        res[k] = typeof v === 'string' ? v : JSON.stringify(v);
    }
    return res;
}

@Injectable({
    providedIn: 'root'
})
export class TmdbService {
    // image url
    public host = 'https://image.tmdb.org/t/p/original/';
    private apiKey: string;

    private async get<T>(url: string, data: object): Promise<HttpResponse<T>> {
        return this.http.get<T>(url, {
            observe: 'response',
            params: {...AlxToObjectString(data), api_key: this.apiKey}
        }).toPromise();
    }

    constructor(private http: HttpClient) {
    }

    init(key: string): this {
        this.apiKey = key;
        return this;
    }

    // _______________________________________________________________________________________________________________________________________
    // Movies ________________________________________________________________________________________________________________________________
    // _______________________________________________________________________________________________________________________________________
    /**
     * recuperation de film ar id
     * @param id
     * @param options
     */
    async getMovie(id: number, options?: MovieQuery): Promise<MovieResponse> {
        const url = `${tmdbApi}/movie/${id}`;
        const res = await this.get<MovieResponse>(url, options);
        return res.body;
    }

    /**
     * Ici on recupère tous les meilleurs films de 2010
     * @param options
     */
    async getAllMovie(options?: MovieQuery): Promise<MovieResponse> {
        const url = `${tmdbApi}/discover/movie?sort_by=popularity.desc`;
        const res = await this.get<MovieResponse>(url, options);
        return res.body;
    }

    /**
     * films dramatique
     * @param options
     */
    async getAllDrama(options?: TVQuery): Promise<MovieResponse> {
        const url = `${tmdbApi}/discover/movie?with_genres=18&sort_by=vote_average.desc&vote_count.gte=10`;
        const res = await this.get<MovieResponse>(url,options);
        return res.body;
    }

    /**
     * films les mieux noté
     * @param query
     */
    async getAllHightRated(options?: TVQuery): Promise<MovieResponse> {
        const url = `${tmdbApi}/discover/movie?certification_country=US&certification=R&sort_by=revenue.desc&with_cast=3896`;
        const res = await this.get<MovieResponse>(url,options);
        return res.body;
    }

    async searchMovie(query: SearchMovieQuery): Promise<SearchMovieResponse> {
        const url = `${tmdbApi}/search/movie`;
        const res = await this.get<SearchMovieResponse>(url, query);
        return res.body;
    }

    // _______________________________________________________________________________________________________________________________________
    // Person / People _______________________________________________________________________________________________________________________
    // _______________________________________________________________________________________________________________________________________
    async getPerson(id: number, options?: PersonQuery): Promise<PersonResponse> {
        const url = `${tmdbApi}/person/${id}`;
        const res = await this.get<PersonResponse>(url, options);
        return res.body;
    }

    async searchPerson(query: SearchPeopleQuery): Promise<SearchPeopleResponse> {
        const url = `${tmdbApi}/search/person`;
        const res = await this.get<SearchPeopleResponse>(url, query);
        return res.body;
    }

    // _______________________________________________________________________________________________________________________________________
    // TV ____________________________________________________________________________________________________________________________________
    // _______________________________________________________________________________________________________________________________________
    async getTV(id: number, options?: TVQuery): Promise<TVResponse> {
        const url = `${tmdbApi}/tv/${id}`;
        const res = await this.get<TVResponse>(url, options);
        return res.body;
    }



    async searchTV(query: SearchTVQuery): Promise<SearchTVResponse> {
        const url = `${tmdbApi}/search/tv`;
        const res = await this.get<SearchTVResponse>(url, query);
        return res.body;
    }

}

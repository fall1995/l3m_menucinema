import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Plats} from '../menu-commade-data/Menu';
import {MovieQuery, MovieResponse} from '../tmdb-data/Movie';

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
export class MenuService {
    public url = `/api/plats`;


    private async get<T>(url: string, data: object): Promise<HttpResponse<T>> {
        return this.http.get<T>(url, {
            observe: 'response',
            params: {...AlxToObjectString(data)}
        }).toPromise();
    }

    constructor(private http: HttpClient) {
    }

    async getAllMenu( options?: MovieQuery): Promise<Plats[]> {
        const url = `/api/plats`;
        const res = await this.get<Plats[]>(url, options);
        return res.body;
    }



}

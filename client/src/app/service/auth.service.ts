import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {User} from '../tmdb-data/user';
import {MovieQuery, MovieResponse} from '../tmdb-data/Movie';

function AlxToObjectString(data?: object): {[key: string]: string} {
    const res = {};
    for (const k of Object.keys(data || {}) ) {
        const v = data[k];
        res[k] = typeof v === 'string' ? v : JSON.stringify(v);
    }
    return res;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
    public client: User;

    private async get<T>(url: string, data: object): Promise<HttpResponse<T>> {
        return this.http.get<T>( url, {
            observe: 'response',
            params: {...AlxToObjectString(data)}
        }).toPromise();
    }

  constructor(private http: HttpClient) { }

    // _______________________________________________________________________________________________________________________________________
    // Authentification ________________________________________________________________________________________________________________________________
    // _______________________________________________________________________________________________________________________________________


    /**
     * envoie des données de connexion au serveur
     * @param userId
     */
    authentificate(params: {[key: string]: string}): Promise<HttpResponse<string>> {
        const P = new HttpParams( {fromObject: params} );
        return this.http.post( `/api/authentification`, P, {
            observe: 'response',
            responseType: 'text',
            headers: {'content-type': 'application/x-www-form-urlencoded'}
        }).toPromise();
    }


    /**
     * recuperation des données su client dans la base par son id
     * @param id
     */
    async getUser(id : string): Promise<User> {
        return new Promise<User>(((resolve, reject) => {
            this.http.get(`/api/authentification?idClient=${id}`, {responseType: 'text'}).toPromise().then(
                res => {
                    console.log("le client " + res);
                    resolve(JSON.parse(res));
                }, rej => {
                    reject(rej);
                }
            );
        }));
    }
  
    /**
     * modification des données
     * @param params
     */
    modification(params: {[key: string]: string}): Promise<HttpResponse<string>> {
        const P = new HttpParams( {fromObject: params} );
        return this.http.put( `/api/authentification`, P, {
            observe: 'response',
            responseType: 'text',
            headers: {'content-type': 'application/x-www-form-urlencoded'}
        }).toPromise();
    }


}

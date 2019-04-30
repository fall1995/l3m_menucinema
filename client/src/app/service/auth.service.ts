import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import * as firebase from 'firebase';
import {User} from '../tmdb-data/user';

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
    private domParser: DOMParser = new DOMParser();
    private doc: Document;
    public client: User;

    private apiKey: string;
    public host = 'http://localhost:8090/';

    private async get<T>(url: string, data: object): Promise<HttpResponse<T>> {
        return this.http.get<T>( url, {
            observe: 'response',
            params: {...AlxToObjectString(data), api_key: this.apiKey}
        }).toPromise();
    }

  constructor(private http: HttpClient) { }

    // _______________________________________________________________________________________________________________________________________
    // Authentification ________________________________________________________________________________________________________________________________
    // _______________________________________________________________________________________________________________________________________



    async login(userId : string, nom : string, prenom : string): Promise<any>{
        // variable que le serveur s'attend a recevoir
        let data = {
            idClient : userId,
            nom : nom,
            prenom : prenom,
        };
        console.log("dans service client " + data.prenom + " - " + data.nom+" - "+data.idClient);
        const res = await this.http.post(`/api/enregistreNouveauClient`, data);
        return res;
    }

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
     * modification des données
     * @param params
     */
    modification(params: {[key: string]: string}): Promise<HttpResponse<string>> {
        const P = new HttpParams( {fromObject: params} );
        return this.http.put( `/api/updateClient`, P, {
            observe: 'response',
            responseType: 'text',
            headers: {'content-type': 'application/x-www-form-urlencoded'}
        }).toPromise();
    }


}

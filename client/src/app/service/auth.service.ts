import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import * as firebase from 'firebase';

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


    /**
     * envoie des données de connexion au serveur
     * @param userId
     */
    login(userId : string): Observable<any>{
        // variable que le serveur s'attend a recevoir
        let data = {
            userId : userId,
        };
        let body = JSON.stringify(data);
        return this.http.post(`/api/authentification`, body);
    }



    /**
     * deconnexion de l'utilisateur
     */
    signOut(){
        firebase.auth().signOut();
    }

}

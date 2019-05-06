import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {User} from '../tmdb-data/user';

@Injectable({
    providedIn: 'root'
})
export class CommandeService {

    constructor(private http: HttpClient) {
    }

    /*
    sendCommande(idClient: string, idPlat: string, idFilms: number, adress: string): Promise<HttpResponse<string>> {
        //const P = new HttpParams({fromObject: params});
        return this.http.post(`/api/commande?idClient=${idClient}&idPlat=${idPlat}&idFilms=${idFilms}&adresseLivraison=${adress}`, {
            observe: 'response',
            responseType: 'text',
            headers: {'content-type': 'application/x-www-form-urlencoded'}
        }).toPromise();
    }
    */

    /**
     * service qui nous permet d'envoyer la commande
     * nous allons inserer les informations dans le corp de la requete
     * @param params
     */
    sendCmd(params: {[key: string]: string}): Promise<HttpResponse<string>> {
        const P = new HttpParams( {fromObject: params} );
        return this.http.post( `/api/`, P, {
            observe: 'response',
            responseType: 'text',
            headers: {'content-type': 'application/x-www-form-urlencoded'}
        }).toPromise();
    }

    /**
     * recuperation de la commande en connaissant l'id du client
     * @param id
     */
    async getCommande(id : string): Promise<User> {
        return new Promise<User>(((resolve, reject) => {
            this.http.get(`/api/authentification?idClient=${id}`, {responseType: 'text'}).toPromise().then(
                res => {
                    console.log("la commande est " + res);
                    resolve(JSON.parse(res));
                }, rej => {
                    reject(rej);
                }
            );
        }));
    }

}

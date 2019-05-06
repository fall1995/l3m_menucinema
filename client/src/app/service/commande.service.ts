import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {User} from '../tmdb-data/user';
import {CommandeData} from '../menu-commade-data/commande';

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
        return this.http.post( `/api/commande`, P, {
            observe: 'response',
            responseType: 'text',
            headers: {'content-type': 'application/x-www-form-urlencoded'}
        }).toPromise();
    }

    /**
     * recuperation de la commande en connaissant l'id du client
     * @param id
     */
    async getCommande(id : string): Promise<CommandeData> {
        return new Promise<CommandeData>(((resolve, reject) => {
            this.http.get(`/api/client?idClient=${id}`, {responseType: 'text'}).toPromise().then(
                res => {
                    resolve(JSON.parse(res));
                }, rej => {
                    reject(rej);
                }
            );
        }));
    }

}

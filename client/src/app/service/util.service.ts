import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import 'rxjs/add/observable/throw';



@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor() { }

    /**
     * Extrait les données de la requête sous forme Json
     * @param res
     * @returns {any|{}}
     */
    public static extractData(res: Response) {
        if (res.status < 200 || res.status >= 300) {
            throw new Error('Mauvais statut de réponse renvoyé par le serveur: ' + res.status);
        }
        const body = res.json();
        return body || {};
    }

    /**
     * Methode gérant les erreurs lors des requêtes HTTPs
     * @param error
     * @returns {ErrorObservable}
     */
    public static handleError(error: any) {
        if (error.status == 403) {
            error.message = error.json().detail;
            error.messageTitle = 'Accès refusé.'
        }
        return Observable.throw(error);
    }

    /**
     * Renvoie les headers à envoyer pour que le serveur sache que l'on traite du JSON
     * @returns {Headers}
     */
    public static getDefaultHeaders(): Headers {
        return new Headers({'Content-Type': 'application/json', 'Accept': 'application/json'});
    }



    /**
     * Renvoie en HTML les erreurs
     * @param erreurs
     * @returns {string}
     */
    public static getErreursEnHtml(erreurs: any): string {
        const html = '<p>';
        const result = UtilService._getErreursEnHtml(erreurs, html);
        return result + '</p>'
    }

    /**
     * Methode récursive pour parcourir les objets et créer un html.
     * @param erreurs
     * @param {string} html
     * @returns {string}
     * @private
     */
    private static _getErreursEnHtml(erreurs: any, html: string): string {

        Object.keys(erreurs).forEach((key, index) => {
            const erreursDuChamp = erreurs[key];

            if (!(erreursDuChamp instanceof Array)) {
                html += UtilService._getErreursEnHtml(erreursDuChamp, html)
            } else {
                erreursDuChamp.forEach(messageErreur => {

                    html += `<li> <strong>${key}:</strong> ${messageErreur}</li>`;
                });
            }
        });
        return html;
    }



}

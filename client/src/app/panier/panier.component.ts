import {Component, OnInit} from '@angular/core';
import {StorageService} from '../service/storage.service';
import {ListePlats} from '../menu-commade-data/Menu';
import {Router} from '@angular/router';
import {CommandeService} from '../service/commande.service';
import {Commande} from '../menu-commade-data/commande';

@Component({
    selector: 'app-panier',
    templateUrl: './panier.component.html',
    styleUrls: ['./panier.component.scss']
})
export class PanierComponent implements OnInit {

    constructor(private storageService: StorageService, private route: Router,
                private commandeService: CommandeService) {
    }

    panier: any[]; // variable qui stocke le tableaux de plat
    movie: any[]; // variable qui stock les films selectionnées
    commande: any;

    ngOnInit() {
        this.init();
    }

    init() {
        this.panier = this.storageService.getMenu();
        this.movie = this.storageService.getMieuNote();
    }

    /**
     * effacer l'élément sélectionné(plat)
     * @param index
     */
    deleteMenusSelected(index: number) {
        this.storageService.delete(index);
        this.init();
    }

    /**
     * effacer l'élément selectionné(movie)
     * @param index
     */
    deleteMovieSelected(index: number) {
        this.storageService.deleteMovie(index);
        this.init();
    }

    /**
     * méthode de validation de la commande
     */
    validationCommande() {
        let idClient = localStorage.getItem('UserData');
        let idPlat = localStorage.getItem('platId');
        console.log('contenu de idPlat' + idPlat);
        let idFilm = localStorage.getItem('movieId');
        console.log('contenu de idPlat' + idFilm);
        let adresse = localStorage.getItem('adresse');
        this.commandeService.sendCmd({
            // variable que le serveur s'attend a recevoir
            idClient: idClient,
            idPlats: idPlat,
            idFilms: idFilm,
            adresseLivraison: adresse,
        }).then(data => {
            console.log('envoie après verification au serveur ok');
        });
        console.log('verification commande pas ok');
    }

    /**
     * bouton de retour vers le menu
     */
    retour() {
        this.route.navigate(['/menus']);
    }
}


import {Component, OnInit} from '@angular/core';
import {StorageService} from '../service/storage.service';
import {ListePlats} from '../menu-commade-data/Menu';
import {Router} from '@angular/router';
import {CommandeService} from '../service/commande.service';
import {Commande} from '../menu-commade-data/commande';
import {AngularFireAuth} from '@angular/fire/auth';
import {MessageService} from 'primeng/api';

@Component({
    selector: 'app-panier',
    templateUrl: './panier.component.html',
    styleUrls: ['./panier.component.scss']
})
export class PanierComponent implements OnInit {

    constructor(private storageService: StorageService, private route: Router, private message: MessageService,
                private commandeService: CommandeService, private afAuth: AngularFireAuth) {
    }

    panier: any[]; // variable qui stocke le tableaux de plat
    movie: any[]; // variable qui stock les films selectionnées
    //commande: any;
    isAuth: boolean; // boolean indiquant s'il est connecté

    ngOnInit() {
        this.init();
        this.afAuth.auth.onAuthStateChanged(
            (user) => {
                if (user) {
                    this.isAuth = true;

                } else {
                    this.isAuth = false;
                }
            }
        );
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
        let idFilm = localStorage.getItem('movieId');
        let adresse = localStorage.getItem('adresse');
        if (this.isAuth){
            this.commandeService.sendCmd({
                // variable que le serveur s'attend a recevoir
                idClient: idClient,
                idPlats: idPlat,
                idFilms: idFilm,
                adresseLivraison: adresse,
            }).then(data => {
                this.message.add({severity:'success',
                    summary:`Commande Confirmer avec succes `,
                    detail:'Merci d\'avoir commandé sur MenuCinema à bientot'});
            });
        } else {
            this.route.navigate(['/authentification/signin']);
        }

    }

    /**
     * bouton de retour vers le menu
     */
    retour() {
        this.route.navigate(['/menus']);
    }
}


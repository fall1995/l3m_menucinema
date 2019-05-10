import {Component, OnInit} from '@angular/core';
import {StorageService} from '../service/storage.service';
import {Plats} from '../menu-commade-data/Menu';
import {Router} from '@angular/router';
import {CommandeService} from '../service/commande.service';
import {Commande} from '../menu-commade-data/commande';
import {AngularFireAuth} from '@angular/fire/auth';
import {MessageService} from 'primeng/api';
import {User} from '../tmdb-data/user';
import {AuthService} from '../service/auth.service';

@Component({
    selector: 'app-panier',
    templateUrl: './panier.component.html',
    styleUrls: ['./panier.component.scss']
})
export class PanierComponent implements OnInit {

    constructor(private storageService: StorageService, private route: Router, private message: MessageService,
                private commandeService: CommandeService, private afAuth: AngularFireAuth,
                private authService: AuthService) {
    }

    panier: any[]; // variable qui stocke le tableaux de plat
    movie: any[]; // variable qui stock les films selectionnées
    //commande: any;
    isAuth: boolean; // boolean indiquant s'il est connecté
    adresse: any;
    afficherDialog = false;
    user: User; // l'utilisateur courant
    totalMenu: any;
    totalMovie: any;
    initRemove: any;

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
        this.adresse = localStorage.getItem('adresse');
        this.initDialog();
        this.totalPanier();
    }

    init() {
        this.panier = this.storageService.getMenu();
        this.movie = this.storageService.getMieuNote();
    }

    totalPanier() {
        this.totalMovie = 0.0;
        this.totalMenu = 0.0;
        let i: number;
        let tabPrixMenu = JSON.parse(localStorage.getItem('totalMenu'));
        for (i = 0; i < tabPrixMenu.length; i++) {
            this.totalMenu += +tabPrixMenu[i];
        }
        let tabPrixMovie = JSON.parse(localStorage.getItem('totalMovie'));
        for (i = 0; i < tabPrixMovie.length; i++) {
            this.totalMovie += tabPrixMovie[i];
        }


    }

    async initDialog() {
        await this.afAuth.user.subscribe(u => {
            if (u) {
                this.authService.getUser(u.uid).then(res => {
                    this.user = res;
                    console.log(this.user);
                }, r => {
                    console.log('errr' + r);
                });
            }
        });
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
        if (this.isAuth) {
            this.commandeService.sendCommande({
                // variable que le serveur s'attend a recevoir
                idClient: idClient,
                idPlats: idPlat,
                idFilms: idFilm,
                adresseLivraison: adresse,
            }).then(data => {
                this.route.navigate(['user/commande']);
                //localStorage.removeItem('plat');
                //localStorage.removeItem('filmNote');
                this.message.add({
                    severity: 'success',
                    summary: `Commande Confirmer avec succes `,
                    detail: 'Merci d\'avoir commandé sur MenuCinema à bientot'
                });
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

    /**
     * bouton qui affiche le dialog pop up
     */
    afficherDialogProfil(): void {
        this.afficherDialog = true;
    }

    /**
     * fermeture du dialog pop up
     */
    onHideProfilDialog(): void {
        this.afficherDialog = false;
    }
}


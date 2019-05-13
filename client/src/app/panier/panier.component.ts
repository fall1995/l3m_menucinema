import {Component, OnInit} from '@angular/core';
import {StorageService} from '../service/storage.service';
import {Router} from '@angular/router';
import {CommandeService} from '../service/commande.service';
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
    total: number = 0;

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
        this.initDialog();
        this.totalPanier();
    }

    init() {
        this.panier = this.storageService.getMenu();
        this.movie = this.storageService.getMieuNote();
        this.adresse = localStorage.getItem('adresse');
    }
    /*
    ici on calcul le total du panier
     */
    totalPanier() {
        this.totalMovie = 0.0;
        this.totalMenu = 0.0;
        let i: number;
        let tabPrixMovie = JSON.parse(localStorage.getItem('totalMovie'));
        for (i = 0; i < tabPrixMovie.length; i++) {
            this.totalMovie += +tabPrixMovie[i];
        }
        let tabPrixMenu = JSON.parse(localStorage.getItem('totalMenu'));
        for (i = 0; i < tabPrixMenu.length; i++) {
            this.totalMenu += +tabPrixMenu[i];
        }
        this.total = this.totalMenu+this.totalMovie;
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
        this.totalPanier();

    }

    /**
     * effacer l'élément selectionné(movie)
     * @param index
     */
    deleteMovieSelected(index: number) {
        this.storageService.deleteMovie(index);
        this.init();
        this.totalPanier();
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


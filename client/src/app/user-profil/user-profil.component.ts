import {Component, OnInit} from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {User} from '../tmdb-data/user';
import {AuthService} from '../service/auth.service';
import {StorageService} from '../service/storage.service';
import {CommandeService} from '../service/commande.service';

@Component({
    selector: 'app-user-profil',
    templateUrl: './user-profil.component.html',
    styleUrls: ['./user-profil.component.scss']
})
export class UserProfilComponent implements OnInit {

    constructor(private afAuth: AngularFireAuth, private authService: AuthService, private storageService: StorageService,
                private commandeService: CommandeService) {
    }

    commade: any; // variable qui stock la dernière commande
    panier: any[]; // variable qui stocke le tableaux de plat
    movie: any[]; // variable qui stock les films selectionnées
    afficherDialog = false; // boolean pour ouvrir et fermer le dialogue pop up
    user: User; // l'utilisateur courant
    userPhoto: User = { // je stock la photo de profil recuperer dans firebase
        photo: '',
    };

    ngOnInit() {
        this.init();
        this.initPanier();
        this.initCommande();
    }

    /**
     * recuperation de la dernière commande
     */
    async initCommande() {
        await this.afAuth.user.subscribe(u => {
            this.commandeService.getLastCommande(u.uid).then(res => {
                this.commade = res;
                this.commade = Array.of(res);
            }, r => {
                console.log('errr' + r);
            });
        });

    }

    /**
     * recuperation des informations de l'utilisateur
     */
    async init() {
        await this.afAuth.user.subscribe(u => {
            if (u) {
                this.authService.getUser(u.uid).then(res => {
                    this.user = res;
                    this.userPhoto.photo = u.photoURL;
                }, r => {
                    console.log('errr' + r);
                });
            }
        });
    }


    initPanier() {
        this.panier = this.storageService.getMenu();
        this.movie = this.storageService.getMieuNote();
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

    /**
     * effacer l'élément sélectionné(plat)
     * @param index
     */
    deleteMenusSelected(index: number) {
        this.storageService.delete(index);
        this.initPanier();
    }

    /**
     * effacer l'élément selectionné(movie)
     * @param index
     */
    deleteMovieSelected(index: number) {
        this.storageService.deleteMovie(index);
        this.initPanier();
    }

}

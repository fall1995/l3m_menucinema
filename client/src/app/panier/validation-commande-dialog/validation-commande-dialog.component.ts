import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {AngularFireDatabase} from '@angular/fire/database';
import {Router} from '@angular/router';
import {AuthService} from '../../service/auth.service';
import {MessageService} from 'primeng/api';
import {User} from '../../tmdb-data/user';
import {StorageService} from '../../service/storage.service';
import {CommandeService} from '../../service/commande.service';

@Component({
  selector: 'app-adresse-update-dialog',
  templateUrl: './validation-commande-dialog.component.html',
  styleUrls: ['./validation-commande-dialog.component.scss']
})
export class ValidationCommandeDialogComponent implements OnInit {

    constructor(private afAuth: AngularFireAuth, private afDatabase : AngularFireDatabase,
                private route: Router, private authService: AuthService, private message : MessageService,
                private storageService: StorageService,private commandeService: CommandeService) {
    }
    isAuth: boolean; // boolean indiquant s'il est connecté

    ngOnInit() {
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

    /**
     * mise à jour de l'utilisateur après modification de l'adresse
     * @param params
     */
    async updateProfile(params: {[key: string]: string}) {
        await this.authService.modification({
            id: params.id,
            nom: params.nom,
            prenom : params.prenom,
            photo : params.photo,
            email : params.email,
            tel : params.tel,
            adresse: params.adresse,

        }).then(res =>{
            localStorage.setItem('adresse', params.adresse);
            //this.afficherDialog = false;
        }, err =>{
            console.log("err");
        });
    }

    /**
     * reference vers l'utilisateur
     */
    @Input() user: User;

    /**
     * boolean pour afficher le dialog
     */
    @Input() afficherDialog = false;

    /**
     * evenement après la fermeture du dialog
     */
    @Output() onDialogHide = new EventEmitter(true);


    /**
     * Méthode appelée lors de la fermeture du dialogue
     */
    onHide(): void {
        this.onDialogHide.emit();
    }

    /**
     * Validation de la commande
     */
    async validationCommande() {
        let idPlat = localStorage.getItem('platId');
        let idFilm = localStorage.getItem('movieId');
        let adresse = localStorage.getItem('adresse');
        await this.afAuth.user.subscribe(u => {
            if (this.isAuth) {
                this.commandeService.sendCommande({
                    idClient: u.uid,
                    idPlats: idPlat,
                    idFilms: idFilm,
                    adresseLivraison: adresse,
                }).then(data => {
                    this.route.navigate(['user/commande']);
                    localStorage.removeItem('plat');
                    localStorage.removeItem('filmNote');
                    localStorage.removeItem('totalMovie');
                    localStorage.removeItem('totalMenu');
                    localStorage.removeItem('movieId');
                    localStorage.removeItem('platId');
                    this.afficherDialog = false;
                    this.message.add({
                        severity: 'success',
                        summary: `Commande Confirmer avec succes `,
                        detail: 'Merci d\'avoir commandé sur MenuCinema à bientot'
                    });
                });
            } else {
                this.route.navigate(['/authentification/signin']);
            }

        });

    }

    /**
     * Quand il n'est pas connecter on le ramene à la page de connexion
     */
    retourSignin(){
        this.route.navigate(['/authentification/signin']);
    }

    DialogHide(){
        this.afficherDialog = false;
    }


}

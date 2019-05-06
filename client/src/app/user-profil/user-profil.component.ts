import {Component, OnInit} from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {User} from '../tmdb-data/user';
import * as firebase from 'firebase';
import {AngularFireDatabase} from '@angular/fire/database';
import {FirebaseObjectObservable} from '@angular/fire/database-deprecated';
import {AuthService} from '../service/auth.service';

@Component({
    selector: 'app-user-profil',
    templateUrl: './user-profil.component.html',
    styleUrls: ['./user-profil.component.scss']
})
export class UserProfilComponent implements OnInit {

    constructor(private afAuth: AngularFireAuth, private authService: AuthService) {
    }

    afficherDialog = false; // boolean pour ouvrir et fermer le dialogue pop up
    user: User; // l'utilisateur courant
    userPhoto : User = { // je stock la photo de profil recuperer dans firebase
        photo: '',
    };
    ngOnInit() {
        this.init();
    }

    /**
     * recuperation des informations de l'utilisateur
     */
     async init() {
         await this.afAuth.user.subscribe( u =>{
            if (u){
                 this.authService.getUser( u.uid).then(res =>{
                     this.user = res;
                     this.userPhoto.photo = u.photoURL;
                    console.log(this.user);
                }, r =>{
                    console.log("errr"+r);
                });
            }
        });
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

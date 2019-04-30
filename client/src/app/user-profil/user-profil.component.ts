import {Component, OnInit} from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {User} from '../tmdb-data/user';
import * as firebase from 'firebase';
import {AngularFireDatabase} from '@angular/fire/database';
import {FirebaseObjectObservable} from '@angular/fire/database-deprecated';

@Component({
    selector: 'app-user-profil',
    templateUrl: './user-profil.component.html',
    styleUrls: ['./user-profil.component.scss']
})
export class UserProfilComponent implements OnInit {

    constructor(private afAuth: AngularFireAuth, private afDatabase: AngularFireDatabase) {
    }
    afficherDialog = false;

    user: User = {
        nom: '',
        prenom: '',
        tel: null,
        adresse: '',
        photo: '',
        email: '',

    };
    public providerId: string;
    ngOnInit() {
        this.afAuth.user.subscribe(u => {
            if (u) {
                this.user.nom =u.displayName;
                this.providerId = u.providerData[0].providerId;
                this.user.email = u.email;
                this.user.photo = u.photoURL;
            }
        });
    }
    afficherDialogProfil(): void {
        this.afficherDialog = true;
    }

    onHideProfilDialog(): void{
        this.afficherDialog = false;
    }

}

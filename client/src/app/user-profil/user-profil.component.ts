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

    afficherDialog = false;
    user: User;
    userPhoto : User = {
        photo: '',
    };
    ngOnInit() {
        //this.initObser();
        this.init();
    }



     async init() {
         await this.afAuth.user.subscribe( u =>{
            if (u){
                 this.authService.getData( u.uid).then( res =>{
                     this.user = res;
                     this.userPhoto.photo = u.photoURL;
                    console.log(this.user);
                }, r =>{
                    console.log("errr"+r);
                });
            }
        });
    }


    afficherDialogProfil(): void {
        this.afficherDialog = true;
    }

    onHideProfilDialog(): void {
        this.afficherDialog = false;
    }

}

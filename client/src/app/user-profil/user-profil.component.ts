import {Component, OnInit} from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {User} from '../tmdb-data/user';

@Component({
    selector: 'app-user-profil',
    templateUrl: './user-profil.component.html',
    styleUrls: ['./user-profil.component.scss']
})
export class UserProfilComponent implements OnInit {

    constructor(private afAuth: AngularFireAuth) {
    }

    user: User = {
        nom: '',
        email: '',

    };
    ngOnInit() {
        this.afAuth.user.subscribe(u => {
            if (u) {
                this.user.nom =u.displayName;
                this.user.email = u.email;
            }
        });
    }

}

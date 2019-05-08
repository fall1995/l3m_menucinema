import {Component, OnInit} from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {AuthService} from '../service/auth.service';
import * as firebase from 'firebase';
import {MessageService} from 'primeng/api';
import {Router} from '@angular/router';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
    isAuth: boolean;
    user: any;
    adresse: any;


    constructor(private afAuth: AngularFireAuth, private authService: AuthService,
    private message: MessageService, private route: Router) {
    }

    init() {
        this.afAuth.user.subscribe(u => {
            if (u) {
                this.user = u.displayName;
            }
        });
    }

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

    onSignOut() {
        this.afAuth.auth.signOut();
        this.message.add({severity:'info',
            summary:'A très bientot',
            detail:'Vous êtes deconneté !'});
        this.route.navigate(['/films']);
        this.init();

    }

}

import { Component, OnInit } from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {auth} from 'firebase';
import {TmdbService} from '../service/tmdb.service';
import {AuthService} from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private afAuth: AngularFireAuth, private authService : AuthService) {
      afAuth.user.subscribe(u => console.log("L'utilisateur est ", u));
  }

    loginGoogle() {
        this.afAuth.auth.signInWithPopup(new auth.GoogleAuthProvider());
        this.verificationServeur();
    }
    verificationServeur(){
        this.afAuth.user.subscribe(utilisateur =>{
            if (utilisateur.uid){

                this.authService.login(utilisateur.uid).subscribe(data =>{
                });
                console.log("verification avec le serveur", utilisateur.emailVerified)
            }
        })
    }

  ngOnInit() {
  }

}
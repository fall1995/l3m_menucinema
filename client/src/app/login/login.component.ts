import { Component, OnInit } from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {auth} from 'firebase';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    // private afAuth: any;

  constructor(private afAuth: AngularFireAuth) {
      afAuth.user.subscribe(u => console.log("L'utilisateur est ", u));
  }

    loginGoogle() {
        this.afAuth.auth.signInWithPopup(new auth.GoogleAuthProvider());
    }

  ngOnInit() {
  }

}

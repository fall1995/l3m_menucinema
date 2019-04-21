import { Component, OnInit } from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {AuthService} from '../service/auth.service';
import * as firebase from 'firebase';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isAuth : boolean;
  constructor(private afAuth: AngularFireAuth, private authService : AuthService) { }

  ngOnInit() {

    this.afAuth.auth.onAuthStateChanged(
        (user) =>{
          if (user){
            this.isAuth = true;
          } else {
            this.isAuth = false;
          }
        }
    );
  }
  onSignOut(){
    this.afAuth.auth.signOut();
  }

}

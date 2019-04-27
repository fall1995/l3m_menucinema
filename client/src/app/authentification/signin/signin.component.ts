import { Component, OnInit } from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {auth} from 'firebase';
import {AuthService} from '../../service/auth.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {TmdbService} from '../../service/tmdb.service';
import {environment} from '../../../environments/environment';
import {MovieResponse} from '../../tmdb-data/Movie';

@Component({
  selector: 'signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

    signInForm : FormGroup;
    errorMessage : string;
    isAuth : boolean;
    listeMovie: MovieResponse;

  constructor(private afAuth: AngularFireAuth, private authService : AuthService,
              private formBuilder: FormBuilder, private route: Router, private tmdbService : TmdbService) {
      afAuth.user.subscribe(u => console.log("L'utilisateur est ", u));
  }

    ngOnInit() {
        this.initForm();
        this.afAuth.auth.onAuthStateChanged(
            (user) =>{
                if (user){
                    this.isAuth = true;
                } else {
                    this.isAuth = false;
                }
            }
        );
        this.init();
    }

    async init() {
        this.tmdbService.init( environment.tmdbKey );
        this.tmdbService.getAllMovie().then(
            data =>{
                this.listeMovie = data;
            }, error =>{
                console.log(error);
            }
        );
    }

    /**
     * login with Google acompt
     */
    loginGoogle() {
        this.afAuth.auth.signInWithPopup(new auth.GoogleAuthProvider()).then(
            () => {
                this.route.navigate(['/profil']);
                this.verificationServeur();
            },
            (error) => {
                this.errorMessage = error;
            }
        );
        // this.verificationServeur();
    }

    /**
     * login with Facebook acompt
     */
    loginFacebook(){
        this.afAuth.auth.signInWithPopup(new auth.FacebookAuthProvider()).then(u =>{

        }, (erro) =>{
            this.errorMessage = erro;
            console.log(erro);
        });
    }
    verificationServeur(){
        this.afAuth.user.subscribe(utilisateur =>{
            if (utilisateur.uid){
                this.authService.authentificate({
                    idClient: utilisateur.uid,
                    nom: utilisateur.displayName,
                    prenom: utilisateur.displayName}).then(data =>{
                    console.log("verification: "+data);
                });
                console.log("verification avec le serveur", utilisateur.emailVerified);
            }
        });
    }
    initForm() {
        this.signInForm = this.formBuilder.group({
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.pattern(/[0-9a-zA-Z]{6,}/)]]
        });
    }

    /**
     * methode de connexion avec l'email et le mot de pass
     */
    onSubmit() {
        const email = this.signInForm.get('email').value;
        const password = this.signInForm.get('password').value;
        this.afAuth.auth.signInWithEmailAndPassword(email,password).then(
            () => {
                this.route.navigate(['/profil']);
                this.verificationServeur();
            },
            (error) => {
                this.errorMessage = error;
            }
        );
    }



}

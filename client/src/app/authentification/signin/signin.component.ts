import { Component, OnInit } from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {auth} from 'firebase';
import {AuthService} from '../../service/auth.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

    signInForm : FormGroup;
    errorMessage : string;

  constructor(private afAuth: AngularFireAuth, private authService : AuthService,
              private formBuilder: FormBuilder, private route: Router) {
      afAuth.user.subscribe(u => console.log("L'utilisateur est ", u));
  }

    ngOnInit() {
        this.initForm();
    }

    /**
     * login with Google acompt
     */
    loginGoogle() {
        this.afAuth.auth.signInWithPopup(new auth.GoogleAuthProvider());
        // this.verificationServeur();
    }

    /**
     * login with Facebook acompt
     */
    loginFacebook(){
        this.afAuth.auth.signInWithPopup(new auth.FacebookAuthProvider());
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
    initForm() {
        this.signInForm = this.formBuilder.group({
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.pattern(/[0-9a-zA-Z]{6,}/)]]
        });
    }

    onSubmit() {
        const email = this.signInForm.get('email').value;
        const password = this.signInForm.get('password').value;
        this.afAuth.auth.signInWithEmailAndPassword(email,password).then(
            () => {
                this.route.navigate(['/films']);
            },
            (error) => {
                this.errorMessage = error;
            }
        );
    }



}

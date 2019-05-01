import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {Router} from '@angular/router';
import {AngularFireAuth} from '@angular/fire/auth';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  signUpForm : FormGroup;
  errorMessage : string;
  constructor(private authService: AuthService, private formBuilder: FormBuilder, private route: Router,
              private afAuth: AngularFireAuth, private messageService: MessageService) { }

  ngOnInit() {
    this.initForm();
  }

    /**
     * Initialisation du formulaire
     */
    initForm() {
        this.signUpForm = this.formBuilder.group({
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.pattern(/[0-9a-zA-Z]{6,}/)]]
        });
    }
    /**
     * Soummission du formulaire
     */
    onSubmit() {
        const email = this.signUpForm.get('email').value;
        const password = this.signUpForm.get('password').value;
        this.afAuth.auth.createUserWithEmailAndPassword(email,password).then(
            () => {
                this.route.navigate(['/films']);
                this.messageService.add({
                    severity:'success',
                    summary: 'Inscription reusit ',
                    detail: 'Bienvenue Vous venez de vous inscrire.'
                });

            },
            (error) => {
                this.messageService.add({
                    severity:'error',
                    summary: 'Erreur d\'inscription',
                    detail: 'Une erreur est survenu lors de votre inscription'
                });
                this.errorMessage = error;
            }
        );
    }

}

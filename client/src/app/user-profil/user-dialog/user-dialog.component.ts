import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from '../../tmdb-data/user';
import {AngularFireAuth} from '@angular/fire/auth';
import {AngularFireDatabase} from '@angular/fire/database';
import {Router} from '@angular/router';
import {FirebaseListObservable} from '@angular/fire/database-deprecated';
import {AuthService} from '../../service/auth.service';

@Component({
    selector: 'app-user-dialog',
    templateUrl: './user-dialog.component.html',
    styleUrls: ['./user-dialog.component.scss']
})
export class UserDialogComponent implements OnInit {

    constructor(private afAuth: AngularFireAuth, private afDatabase : AngularFireDatabase,
                private route: Router, private authService: AuthService) {
        this.afAuth.authState.subscribe(user =>{
            if (user) { this.user.id = user.uid; }
        });
    }

    ngOnInit() {
    }

    cupdateProfile() {
        this.afAuth.authState.subscribe( user =>{
            if (user.uid){
                this.authService.modification({
                   idClient : user.uid,
                });
            }
            this.onHide();
            this.route.navigate(['/profil']);
            //this.afDatabase.object(`user/${this.user.id}`).set(this.user).then( () =>{

            //});
        });
    }

    // user = {} as User;

    /**
     * reference vers l'utilisateur
     */
    @Input() user: User;

    /**
     * boolean pour afficher le dialog
     */
    @Input() afficherDialog = false;

    /**
     * evenement après la fermeture du dialog
     */
    @Output() onDialogHide = new EventEmitter(true);

    /**
     * evenement après modification
     */
    @Output() profilModifieravecSucces = new EventEmitter<User>(true);

    /**
     * Méthode appelée lors de la fermeture du dialogue
     */
    onHide(): void {
        this.onDialogHide.emit();
    }

    /**
     * Méthode appelée lors de l'ouverture du dialogue
     */
    onShow(): void {
    }


}

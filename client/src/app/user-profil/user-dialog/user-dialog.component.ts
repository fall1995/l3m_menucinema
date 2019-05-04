import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from '../../tmdb-data/user';
import {AngularFireAuth} from '@angular/fire/auth';
import {AngularFireDatabase} from '@angular/fire/database';
import {Router} from '@angular/router';
import {FirebaseListObservable} from '@angular/fire/database-deprecated';
import {AuthService} from '../../service/auth.service';
import {MessageService} from 'primeng/api';

@Component({
    selector: 'app-user-dialog',
    templateUrl: './user-dialog.component.html',
    styleUrls: ['./user-dialog.component.scss']
})
export class UserDialogComponent implements OnInit {

    //user: User;

    constructor(private afAuth: AngularFireAuth, private afDatabase : AngularFireDatabase,
                private route: Router, private authService: AuthService, private message : MessageService) {
        this.afAuth.authState.subscribe(user =>{
            if (user) { this.user.id = user.uid; }
        });
    }

    ngOnInit() {
    }

    async updateProfile(params: {[key: string]: string}) {
        await this.authService.modification({
            id: params.id,
            nom: params.nom,
            prenom : params.prenom,
            photo : params.photo,
            email : params.email,
            tel : params.tel,
            adresse: params.adress,
        }).then(res =>{
            this.afficherDialog = false;
            this.message.add({severity:'success',
                summary:`Modification du profil OK `,
                detail:'Vous avez bien modifié votre profil'});
            console.log(res);
        }, err =>{
            console.log("err");
            this.message.add({severity:'error',
                summary:`Erreur de Modification `,
                detail:'Une erreur est survenue lors de la modification de votre profil'});
        });
        this.message.add({severity:'success',
            summary:`Modification du profil OK `,
            detail:'Vous avez bien modifié votre profil'});

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

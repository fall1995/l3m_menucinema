import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {AngularFireDatabase} from '@angular/fire/database';
import {Router} from '@angular/router';
import {AuthService} from '../../service/auth.service';
import {MessageService} from 'primeng/api';
import {User} from '../../tmdb-data/user';

@Component({
  selector: 'app-adresse-update-dialog',
  templateUrl: './adresse-update-dialog.component.html',
  styleUrls: ['./adresse-update-dialog.component.scss']
})
export class AdresseUpdateDialogComponent implements OnInit {

    constructor(private afAuth: AngularFireAuth, private afDatabase : AngularFireDatabase,
                private route: Router, private authService: AuthService, private message : MessageService) {
    }

    ngOnInit() {
    }

    /**
     * mise à jour de l'utilisateur après modification de l'adresse
     * @param params
     */
    async updateProfile(params: {[key: string]: string}) {
        await this.authService.modification({
            id: params.id,
            nom: params.nom,
            prenom : params.prenom,
            photo : params.photo,
            email : params.email,
            tel : params.tel,
            adresse: params.adresse,

        }).then(res =>{
            localStorage.setItem('adresse', params.adresse);
            this.afficherDialog = false;
            console.log(res);
        }, err =>{
            console.log("err");
        });
    }

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

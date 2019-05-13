import {Component, OnInit} from '@angular/core';
import {CommandeService} from '../service/commande.service';
import {CommandeData} from '../menu-commade-data/commande';
import {AngularFireAuth} from '@angular/fire/auth';

@Component({
    selector: 'app-commande',
    templateUrl: './commande.component.html',
    styleUrls: ['./commande.component.scss']
})
export class CommandeComponent implements OnInit {

    commade: CommandeData; // variable qui va stocker les commande

    constructor(private commandeService: CommandeService, private afAuth: AngularFireAuth) {
    }

    ngOnInit() {
        this.init();
    }

    /**
     * je recupere ici les données de la commande
     */
    async init() {
        await this.afAuth.user.subscribe(u => {
            this.commandeService.getCommande(u.uid).then(res => {
                this.commade = res;
                console.log(res);
            }, r => {
                console.log('errr' + r);
            });

        });

    }


}

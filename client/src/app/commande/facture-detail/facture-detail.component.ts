import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {CommandeData} from '../../menu-commade-data/commande';
import {CommandeService} from '../../service/commande.service';
import {AuthService} from '../../service/auth.service';
import {AngularFireAuth} from '@angular/fire/auth';
import {User} from '../../tmdb-data/user';

@Component({
    selector: 'app-facture-detail',
    templateUrl: './facture-detail.component.html',
    styleUrls: ['./facture-detail.component.scss']
})
export class FactureDetailComponent implements OnInit {

    constructor(private route: ActivatedRoute, private commandeService: CommandeService,
                private authService: AuthService, private afAuth: AngularFireAuth) {
    }
    commande: any; // la commande courante
    user: User; // l'utilisateur courant

    ngOnInit() {
        this.route.params.subscribe(params => {
            this.getFacture(params['idCommande']); //  recuperation de l'id passé par l'url
        });
        this.init();
    }

    /**
     * recuperation de la facture par l'id de la commande
     * @param idCommande
     */
     getFacture(idCommande: string) {
        this.commandeService.getCommandeById(idCommande).then(res => {
            this.commande = res;
            this.commande = Array.of(res);
        }, r => {
            console.log('errr' + r);
        });
    }

    /**
     * recuperation des informations de l'utilisateur
     */
    async init() {
        await this.afAuth.user.subscribe(u => {
            if (u){
                this.authService.getUser( u.uid).then(res =>{
                    this.user = res;
                }, r =>{
                    console.log("errr"+r);
                });
            }
        });
    }



    imprimer(){
        window.print();
    }

}

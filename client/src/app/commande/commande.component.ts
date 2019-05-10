import {Component, OnInit} from '@angular/core';
import {CommandeService} from '../service/commande.service';
import {CommandeData} from '../menu-commade-data/commande';

@Component({
    selector: 'app-commande',
    templateUrl: './commande.component.html',
    styleUrls: ['./commande.component.scss']
})
export class CommandeComponent implements OnInit {

    commade: CommandeData; // variable qui va stocker les commande

    constructor(private commandeService: CommandeService) {
    }

    ngOnInit() {
        this.init();
    }

    /**
     * je recupere ici les données de la commande
     */
    async init() {
        let idClient = localStorage.getItem('UserData');
        this.commandeService.getCommande(idClient).then(res => {
            this.commade = res;
            console.log(res);
        }, r => {
            console.log('errr' + r);
        });
    }

    factureSelected(){
        window.print();
    }

}

import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {CommandeData} from '../../menu-commade-data/commande';
import {CommandeService} from '../../service/commande.service';

@Component({
    selector: 'app-facture-detail',
    templateUrl: './facture-detail.component.html',
    styleUrls: ['./facture-detail.component.scss']
})
export class FactureDetailComponent implements OnInit {

    constructor(private route: ActivatedRoute, private commandeService: CommandeService) {
    }

    commande: CommandeData;

    ngOnInit() {
        this.route.params.subscribe(params => {
            this.getFacture(params['idCommande']);
        });
    }

    getFacture(idCommande: number) {

    }

}

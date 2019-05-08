import {Component, OnInit} from '@angular/core';
import {MenuService} from '../service/menu.service';
import {Plats} from '../menu-commade-data/Menu';
import {MovieResponse} from '../tmdb-data/Movie';
import {MessageService, SelectItem} from 'primeng/api';
import {StorageService} from '../service/storage.service';

@Component({
    selector: 'app-menus',
    templateUrl: './menus.component.html',
    styleUrls: ['./menus.component.scss']
})
export class MenusComponent implements OnInit {

    /**
     *variable dans le quel on stock les plats après les avoirs récupéré
     */
    plats: Plats[];
    selectedPlat: Plats; // plat selectionner
    aff = false;

    sortOptions: SelectItem[];

    displayDialog: boolean;

    sortKey: string;

    sortField: string;

    sortOrder: number;

    constructor(private menuService: MenuService, private storageService: StorageService,
                private message: MessageService) {
    }

    ngOnInit() {
        this.init();
        this.sortOptions = [
            {label: 'Nom', value: 'id'},
            {label: 'Type plat', value: 'type'},
            {label: 'Prix', value: 'prix'},
        ];
    }

    selectCar(plat: Plats) {
        this.selectedPlat = plat;
        this.displayDialog = true;
        event.preventDefault();
    }

    /**
     *
     * @param event
     */
    onSortChange(event) {
        let value = event.value;
        if (value.indexOf('!') === 0) {
            this.sortOrder = -1;
            this.sortField = value.substring(1, value.length);
        }
        else {
            this.sortOrder = 1;
            this.sortField = value;
        }
    }

    async init() {
        await this.menuService.getAllMenu().then(res => {
            this.plats = res;
            this.aff = true;
            console.log(this.plats);
        }, err => {
            console.log('error de recup');
        });
    }

    /**
     * methode d'ajout du plat dans le panier après selection
     * @param id
     */
    addMenus(id: string) {
        let plat = this.plats.find(plats => plats.id === id);
        this.storageService.addMenu(plat);
        this.message.add({severity:'success',
            summary:`Ajout de ${plat.id} `,
            detail:'Votre choix a bien été ajouter dans votre panier'});

    }

}

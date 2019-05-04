import {Component, OnInit} from '@angular/core';
import {MenuService} from '../service/menu.service';
import {ListePlats} from '../menu-commade-data/Menu';
import {MovieResponse} from '../tmdb-data/Movie';
import {SelectItem} from 'primeng/api';

@Component({
    selector: 'app-menus',
    templateUrl: './menus.component.html',
    styleUrls: ['./menus.component.scss']
})
export class MenusComponent implements OnInit {

    /**
     *variable dans le quel on stock les plats après les avoirs récupéré
     */
    listePlats: ListePlats;
    aff = false;

    sortOptions: SelectItem[];

    sortKey: string;

    sortField: string;

    sortOrder: number;

    constructor(private menuService: MenuService) {

    }

    ngOnInit() {
        this.init();
        this.sortOptions = [
            {label: 'id', value: 'id'},
            {label: 'Type plat', value: 'type'},
            {label: 'Prix', value: 'prix'}
        ];
    }

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
            this.listePlats = res;
            this.aff = true;
            console.log(this.listePlats);
        }, err => {
            console.log('error de recup');
        });
    }

    get plats(): ListePlats {
        return this.listePlats;
    }

}

import {Component, OnInit} from '@angular/core';
import {StorageService} from '../service/storage.service';
import {ListePlats} from '../menu-commade-data/Menu';
import {Router} from '@angular/router';

@Component({
    selector: 'app-panier',
    templateUrl: './panier.component.html',
    styleUrls: ['./panier.component.scss']
})
export class PanierComponent implements OnInit {

    constructor(private storageService: StorageService, private route: Router) {
    }

    panier: any[];
    movie: any[];


    ngOnInit() {
        this.init();
    }
    init(){
        this.panier = this.storageService.getMenu();
        this.movie = this.storageService.getMieuNote();
    }

    deleteMenusSelected(index: number) {
        this.storageService.delete(index);
        this.init();
    }

    deleteMovieSelected(index: number){
        this.storageService.deleteMovie(index);
        this.init();
    }

    retour(){
        this.route.navigate(['/dashbord']);
    }

}

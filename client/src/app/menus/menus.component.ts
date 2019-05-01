import { Component, OnInit } from '@angular/core';
import {MenuService} from '../service/menu.service';
import { ListePlats} from '../menu-commade-data/Menu';
import {MovieResponse} from '../tmdb-data/Movie';

@Component({
  selector: 'app-menus',
  templateUrl: './menus.component.html',
  styleUrls: ['./menus.component.scss']
})
export class MenusComponent implements OnInit {

  listePlats: ListePlats;
  aff = false;

  constructor(private menuService : MenuService) {

  }

  ngOnInit() {

    this.init().then(res => {
    });
  }
  async init(){
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

import { Component, OnInit } from '@angular/core';
import {MenuService} from '../service/menu.service';
import {DataMenu, ListePlats} from '../menu-commade-data/Menu';
import {MovieResponse} from '../tmdb-data/Movie';

@Component({
  selector: 'app-menus',
  templateUrl: './menus.component.html',
  styleUrls: ['./menus.component.scss']
})
export class MenusComponent implements OnInit {

  listePlats: ListePlats;

  constructor(private menuService : MenuService) {

  }

  ngOnInit() {

    this.init();
  }
  async init(){
    this.listePlats = await this.menuService.getAllMenu();
  }
    get plats(): ListePlats {
        return this.listePlats;
    }

}

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SigninComponent} from './authentification/signin/signin.component';
import {SignupComponent} from './authentification/signup/signup.component';
import {FilmsComponent} from './films/films.component';
import {MenusComponent} from './menus/menus.component';
import {SingleMovieComponent} from './single-movie/single-movie.component';

const routes: Routes = [
    {path: 'authentification/signin', component: SigninComponent},
    {path: 'authentification/signup', component: SignupComponent},
    {path: 'films', component: FilmsComponent},
    {path: 'menus', component: MenusComponent},
    {path: 'films/view/:id', component: SingleMovieComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

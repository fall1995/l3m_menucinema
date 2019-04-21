import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {TmdbService} from './service/tmdb.service';
import {HttpClientModule} from '@angular/common/http';
import {AngularFireModule} from '@angular/fire';
import {environment} from '../environments/environment';
import { SigninComponent } from './authentification/signin/signin.component';
import {AngularFireAuthModule} from '@angular/fire/auth';
import {AngularFireDatabaseModule} from '@angular/fire/database';
import {AngularFirestoreModule} from '@angular/fire/firestore';
import {AngularFireStorageModule} from '@angular/fire/storage';
import {AngularFireMessagingModule} from '@angular/fire/messaging';
import { SignupComponent } from './authentification/signup/signup.component';
import { FilmsComponent } from './films/films.component';
import { MenusComponent } from './menus/menus.component';
import { HeaderComponent } from './header/header.component';
import {AuthService} from './service/auth.service';
import {MenuService} from './service/menu.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { SingleMovieComponent } from './single-movie/single-movie.component';
import * as firebase from 'firebase';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
@NgModule({
    declarations: [
        AppComponent,
        SigninComponent,
        SignupComponent,
        FilmsComponent,
        MenusComponent,
        HeaderComponent,
        SingleMovieComponent,
    ],
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        AngularFontAwesomeModule,
        AppRoutingModule,
        HttpClientModule,
        AngularFireStorageModule,
        AngularFireMessagingModule,
        AngularFireAuthModule,
        AngularFireDatabaseModule,
        AngularFirestoreModule,
        AngularFireModule.initializeApp(environment.firebase,'app-signup')
    ],
    providers: [TmdbService,AuthService,MenuService],
    bootstrap: [AppComponent]
})
export class AppModule {

}

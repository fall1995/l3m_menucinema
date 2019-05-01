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
import {AuthGuardService} from './service/auth-guard.service';
import { UserProfilComponent } from './user-profil/user-profil.component';
import { HomeComponent } from './home/home.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatCheckboxModule, MatIconModule,} from '@angular/material';
import {MatCardModule} from '@angular/material/card';
import {MatTabsModule} from '@angular/material/tabs';
import {CardModule} from 'primeng/card';
import {ButtonModule} from 'primeng/button';
import {DialogModule} from 'primeng/dialog';


import 'hammerjs';
import { UserDialogComponent } from './user-profil/user-dialog/user-dialog.component';
import {OverlayPanelModule} from 'primeng/primeng';
@NgModule({
    declarations: [
        AppComponent,
        SigninComponent,
        SignupComponent,
        FilmsComponent,
        MenusComponent,
        HeaderComponent,
        SingleMovieComponent,
        UserProfilComponent,
        HomeComponent,
        UserDialogComponent,
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
        AngularFireModule.initializeApp(environment.firebase),
        BrowserAnimationsModule,
        MatButtonModule,
        MatCheckboxModule,
        MatCardModule,
        MatTabsModule,
        CardModule,
        DialogModule,
        ButtonModule,
        MatIconModule,
        OverlayPanelModule,

    ],
    providers: [TmdbService,AuthService,MenuService, AuthGuardService],
    bootstrap: [AppComponent]
})
export class AppModule {

}

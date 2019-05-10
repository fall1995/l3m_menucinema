import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {AngularFireModule} from '@angular/fire';
import {environment} from '../environments/environment';
// component
import { AppComponent } from './app.component';
import { SigninComponent } from './authentification/signin/signin.component';
import { SignupComponent } from './authentification/signup/signup.component';
import { FilmsComponent } from './films/films.component';
import { MenusComponent } from './menus/menus.component';
import { HeaderComponent } from './header/header.component';
// service
import {StorageService} from './service/storage.service';
import {TmdbService} from './service/tmdb.service';
import {AuthService} from './service/auth.service';
import {MenuService} from './service/menu.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { SingleMovieComponent } from './single-movie/single-movie.component';
// importation de firebase
import {AngularFireAuthModule} from '@angular/fire/auth';
import {AngularFireDatabaseModule} from '@angular/fire/database';
import {AngularFirestoreModule} from '@angular/fire/firestore';
import {AngularFireStorageModule} from '@angular/fire/storage';
import {AngularFireMessagingModule} from '@angular/fire/messaging';
import * as firebase from 'firebase';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {AuthGuardService} from './service/auth-guard.service';
import { UserProfilComponent } from './user-profil/user-profil.component';
import { HomeComponent } from './home/home.component';

// importation des modules de material design
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatCheckboxModule, MatIconModule,} from '@angular/material';
import {MatCardModule} from '@angular/material/card';
import {MatTabsModule} from '@angular/material/tabs';

// importation des module de primeng
import {CardModule} from 'primeng/card';
import {ButtonModule} from 'primeng/button';
import {DialogModule} from 'primeng/dialog';
import {ToastModule} from 'primeng/toast';
import {MessageService} from 'primeng/api';
import {DataViewModule} from 'primeng/dataview';
import {DropdownModule} from 'primeng/dropdown';
import {PanelModule} from 'primeng/panel';
import {TableModule} from 'primeng/table';



import 'hammerjs';
import { UserDialogComponent } from './user-profil/user-dialog/user-dialog.component';
import {FieldsetModule, OverlayPanelModule} from 'primeng/primeng';
import { CommandeComponent } from './commande/commande.component';
import { PanierComponent } from './panier/panier.component';
import { AdresseUpdateDialogComponent } from './panier/adresse-update-dialog/adresse-update-dialog.component';

import { FactureDetailComponent } from './commande/facture-detail/facture-detail.component';


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
        CommandeComponent,
        PanierComponent,
        AdresseUpdateDialogComponent,

        FactureDetailComponent,

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
        FieldsetModule,
        ToastModule,
        DataViewModule,
        DropdownModule,
        PanelModule,
        TableModule

    ],
    providers: [TmdbService, MessageService,AuthService,MenuService, AuthGuardService, StorageService],
    bootstrap: [AppComponent]
})
export class AppModule {

}

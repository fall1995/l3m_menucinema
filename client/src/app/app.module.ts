import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {TmdbService} from './service/tmdb.service';
import {HttpClientModule} from '@angular/common/http';
import {AngularFireModule} from '@angular/fire';
import {environment} from '../environments/environment';
import { LoginComponent } from './login/login.component';
import {AngularFireAuthModule} from '@angular/fire/auth';
import {AngularFireDatabaseModule} from '@angular/fire/database';
import {AngularFirestoreModule} from '@angular/fire/firestore';
import {AngularFireStorageModule} from '@angular/fire/storage';
import {AngularFireMessagingModule} from '@angular/fire/messaging';

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        AngularFireStorageModule,
        AngularFireMessagingModule,
        AngularFireAuthModule,
        AngularFireDatabaseModule,
        AngularFirestoreModule,
        AngularFireModule.initializeApp(environment.firebase, 'LoginComponent')
    ],
    providers: [TmdbService],
    bootstrap: [AppComponent]
})
export class AppModule {

}

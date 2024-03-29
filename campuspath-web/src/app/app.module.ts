import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { InfoMenuComponent } from './info-menu/info-menu.component';
import { AppRoutingModule } from './app-routing.module';
import { MapComponent } from './map/map.component';
import { SearchMenuComponent } from './search-menu/search-menu.component';
import { APIService } from './api-service.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { InfoPopupComponent } from './info-popup/info-popup.component';
import { PrivacyPopupComponent } from './privacy-popup/privacy-popup.component';
import { AboutUsPopupComponent } from './about-us-popup/about-us-popup.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { ArrivalPopupComponent } from './arrival-popup/arrival-popup.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    InfoMenuComponent,
    MapComponent,
    SearchMenuComponent,
    InfoPopupComponent,
    PrivacyPopupComponent,
    AboutUsPopupComponent,
    SearchBarComponent,
    ArrivalPopupComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,
    FormsModule
  ],
  providers: [APIService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

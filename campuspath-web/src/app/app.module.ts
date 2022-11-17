import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { InfoMenuComponent } from './info-menu/info-menu.component';
import { AppRoutingModule } from './app-routing.module';
import { MapComponent } from './map/map.component';
import { SearchMenuComponent } from './search-menu/search-menu.component';
import { GeoService } from './geo-service.service';


@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    InfoMenuComponent,
    MapComponent,
    SearchMenuComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [GeoService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { InfoMenuComponent } from './info-menu/info-menu.component';
import { AppRoutingModule } from './app-routing.module';
import { MapComponent } from './map/map.component';
import { SearchMenuComponent } from './search-menu/search-menu.component';

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
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

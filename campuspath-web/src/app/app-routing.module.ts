import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { InfoMenuComponent } from './info-menu/info-menu.component';
import { SearchMenuComponent } from './search-menu/search-menu.component';
import { MapComponent } from './map/map.component';
import { InfoPopupComponent } from './info-popup/info-popup.component';

const routes: Routes = [
  { path: '', component: MenuComponent},
  { path: 'info', component: InfoMenuComponent },
  { path: 'search', component: SearchMenuComponent },
  { path: 'map', component: MapComponent },
  { path: 'debug', component: InfoPopupComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

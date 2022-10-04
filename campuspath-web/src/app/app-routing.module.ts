import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainMenuComponent } from './MainMenu/main-menu.component';
import { MapComponent } from './Map/map.component';
import { InfoComponent } from './Info/info.component';

const routes: Routes = [
  {path: 'MainMenu', component: MainMenuComponent},
  {path: 'Map', component: MapComponent},
  {path: 'Info', component: InfoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

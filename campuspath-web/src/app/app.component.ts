import { Component } from '@angular/core';

//import { GeolocationService } from '@ng-web-apis/geolocation';
import { MenuComponent } from './menu/menu.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'CampusPath';

  
  constructor(/*private readonly geolocation$: GeolocationService*/) {
  }

  
  /*getPosition() {
    this.geolocation$.subscribe(position => {
      //doSomethingWithPosition(position);
      this.lat = position.coords.latitude;
      this.lng = position.coords.longitude;

    });
  }
  
  watchPosition(){ //second function to test if first is not working.
    this.geolocation$.watchPosition(position =>{

      this.lat = position.coords.latitude;
      this.lng = position.coords.longitude;
      
    });
  }
  */
  
}

import { Component } from '@angular/core';
import { environment } from '../environments/environment';

//import { GeolocationService } from '@ng-web-apis/geolocation';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'CampusPath';



  /*
  getPosition() {
    this.geolocation$.subscribe(position => {
      doSomethingWithPosition(position);
    });
  }
  */
}

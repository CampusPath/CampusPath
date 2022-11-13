import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(private readonly geolocation$: GeolocationService) { }

  ngOnInit(): void {
    this.grabUserCoordinates();

  }
   grabUserCoordinates() { //function that executes once user clicks "Start"
    
    AppComponent.getPosition();
    
    console.log("Latitude : " + AppComponent.lat);
    console.log("Longitude : " + AppComponent.long);
    //this.geolocation$.subscribe();
    

    
  }

}

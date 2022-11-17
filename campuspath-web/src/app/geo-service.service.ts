import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { GeolocationService } from '@ng-web-apis/geolocation';

//import { MapComponent } from './map.component';


/* geolocation documentation used as a reference:
  https://developer.mozilla.org/en-US/docs/Web/API/Geolocation_API/Using_the_Geolocation_API
*/

@Injectable(
  {providedIn: 'root'}
)
export class GeoService {
  //geoLocation$: Subject<any> = new Subject<any>();
  private started = false;
  public lat: number = 0;
  public lng: number = 0;

  constructor() {
    navigator.geolocation.getCurrentPosition(success, error);
  }

  //sets lat and lng when a position is succesfully found
  function success(position) {
    lat = position.coords.latitude;
    lng = position.coords.longitude;
  }

  //called if watchID cant find a position
  function error() {
    console.log("Could not get position.");
  }

  //options for watchPostion, these might need to be played with later
  //  enableHighAccuracy: boolean
  //  maximumAge: how long to reuse a position value before refreshing
  //  timeout: how long to try and get browser location data for before timing out
  const options = {
    enableHighAccuracy: true,
    maximumAge: 30000,
    timeout: 27000
  }

  //watch position function. Calls either success or error function depening on if it can find a user position
  //watchPosition is called more than once, so it will update lat and lng periodically
  const watchID = navigator.geolocation.watchPosition(success, error, options);



  /*
  getUserLocation() {
    if (!this.started) {
      this.started = true;
      navigator.geolocation.watchPosition(
        (position) => {
          this.geoLocation$.next(position);
          this.lat = position.coords.latitude;
          this.lng = position.coords.longitude;
          console.log("Coords: " + this.lat +" " +  this.lng);
        },
        (err) => {
          this.geoLocation$.error(err);
        },

      );
    }
  }*/
}

import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { GeolocationService } from '@ng-web-apis/geolocation';

import { MapComponent } from './map/map.component';
import { Coords } from './coords';
import { COORDS } from './coords-instance';


/* geolocation documentation used as a reference:
  https://developer.mozilla.org/en-US/docs/Web/API/Geolocation_API/Using_the_Geolocation_API
*/

@Injectable(
  {providedIn: 'root'}
)
export class GeoService {
  //geoLocation$: Subject<any> = new Subject<any>();
  //private started = false;
  //public lat: number = 0;
  //public lng: number = 0;

  constructor(private geolocationService: GeolocationService) {
    navigator.geolocation.getCurrentPosition(this.success, this.error);
  }

  //getter for coords
  getCoords(): Observable<Coords> {
    const coords = of(COORDS);
    return coords;
  }

  //sets lat and lng when a position is succesfully found
  success(position: any): void {
    console.log("Found position!");
    COORDS.lat = position.coords.latitude;
    COORDS.lng = position.coords.longitude;
    //console.log("Lat: " + COORDS.lat);
    //console.log("Lng: " + COORDS.lng);
  }

  //called if watchID cant find a position
  error(): void {
    console.log("Could not get position.");
  }

  //options for watchPostion, these might need to be played with later
  //  enableHighAccuracy: boolean
  //  maximumAge: how long to reuse a position value before refreshing
  //  timeout: how long to try and get browser location data for before timing out
  options = {
    enableHighAccuracy: true,
    maximumAge: 30000,
    timeout: 27000
  }

  //watch position function. Calls either success or error function depening on if it can find a user position
  //watchPosition is called more than once, so it will update lat and lng periodically
  watchID = navigator.geolocation.watchPosition(this.success, this.error, this.options);

}

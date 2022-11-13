import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable()
export class GeoService {
  geoLocation$: Subject<any> = new Subject<any>();
  private started = false;
  public lat: any;
  public lng: any;
  constructor() {
  }

  getUserLocation(options?: any) {
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
        options
      );
    }
  }



}

import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';


@Injectable()
export class GeoService {
  geoLocation$: Subject<any> = new Subject<any>();
  private started = false;
  constructor() {
  }

  getUserLocation(options?: any) {
    if (!this.started) {
      this.started = true;
      navigator.geolocation.watchPosition(
        (position) => {
          this.geoLocation$.next(position);
        },
        (err) => {
          this.geoLocation$.error(err);
        },
        options
      );
    }
  }

}
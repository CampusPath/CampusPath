import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { GeoJSONSource, GeolocateControl, LngLatBounds, Map } from 'maplibre-gl';

import { APIService } from '../api-service.service';
import { LngLat, V1 } from '../search';
import { Feature, LineString } from 'geojson';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit, AfterViewInit, OnDestroy {
  map: Map | undefined;

  // Dynamic routing stuff
  route: V1.Route | undefined;
  userCoords: GeolocationCoordinates | undefined;
  routeLineData: Feature<LineString>;

  @ViewChild('map')
  private mapContainer!: ElementRef<HTMLElement>;

  constructor(private apiService: APIService) {
    this.routeLineData = {
      type: 'Feature',
      properties: {},
      geometry: {
        type: 'LineString',
        coordinates: []
      }
    };
  }

  ngOnInit(): void {
  }

  //display the map
  ngAfterViewInit() {
    // TODO: Fetch initial coords from the campusId via apiService.getCampus(...)
    this.map = new Map({
      container: this.mapContainer.nativeElement,
      style: `https://api.maptiler.com/maps/streets/style.json?key=HfAVSwePVPgGxhY1Yvjw`,
      center: [-95.251963, 32.315867],
      zoom: 16,
      pitch: 45
    });

    let geolocate = new GeolocateControl({
      positionOptions: {
        enableHighAccuracy: true,
        maximumAge: 30000,
        timeout: 27000
      },
      trackUserLocation: true
    });

    // Setup relevant event callbacks
    geolocate.on('trackuserlocationend', () => {
      this.userCoords = undefined;
    });
    geolocate.on('geolocate', (position: GeolocationPosition) => {
      this.userCoords = position.coords;
      this.updateRoute();
    });
    // Add the control and request the user's location upon map load
    this.map.addControl(geolocate);
    this.map.on('load', () => {
      this.map?.addSource('route', {
        'type': 'geojson',
        'data': this.routeLineData
      });
      this.map?.addLayer({
        'id': 'route',
        'type': 'line',
        'source': 'route',
        'layout': {
          'line-join': 'round',
          'line-cap': 'round'
        },
        'paint': {
          'line-color': '#3399ff',
          'line-width': 6
        }
      });
      geolocate.trigger();
    });
  }

  //remove the map
  ngOnDestroy() {
    this.map?.remove();
  }

  routeTo(dest: string): void {
    if (this.userCoords === undefined) {
      // TODO: ERROR: User location not available, can't route
      return;
    }
    // TODO: Select Destination => Acquire Direction => Go! Route
    this.apiService.route(this.userCoords?.latitude, this.userCoords?.longitude, dest).subscribe((route: V1.Route) => {
      this.route = route;
      let coords = this.route.path;

      // Fit the screen to the route
      let bounds = this.route.path.reduce((bounds: LngLatBounds, coord: LngLat) => {
        return bounds.extend(coord);
      }, new LngLatBounds(coords[0], coords[0]));

      this.map?.fitBounds(bounds, {
        padding: 50
      });

      // Update the line
      this.redrawPath(coords);
    });
  }

  redrawPath(coords: LngLat[]): void {
    this.routeLineData.geometry.coordinates = coords;
    (this.map?.getSource('route') as GeoJSONSource).setData(this.routeLineData);
  }

  // check if |user.lat - param lat| and |user.lng - param lng| are within some nearness threshold
  // this will need tweaking
  // 0.0001 roughly equals 11.1 meters, so this will have about 22.2 meters leeway
  // might be too much or too little, itll have to be tested
  latThresh: number = 0.00005;
  lngThresh: number = 0.00005;

  userIsNear(pos: LngLat): boolean {
    if (this.userCoords === undefined) {
      return false;
    }
    let lngDiff = Math.abs(this.userCoords.longitude - pos[0]);
    let latDiff = Math.abs(this.userCoords.latitude - pos[1]);
    return ((latDiff <= this.latThresh) && (lngDiff <= this.lngThresh));
  }

  /*
    ETHAN'S OLD NOTES:::
      1. Check if the user is close enough to route[counter]
        a. if yes, "remove" route[counter] by ++counter
        b. else skip if statement
      2. Create a temp route variable
      3. Add the user as the first point
      4. Add all points after route[counter] from route
      5. Return the newly updated route as a number[][]

      NOTE counter will need to be reset whenever there is a new end destination
    */
  updateRoute() {
    if (this.userCoords === undefined || this.route === undefined) {
      return;
    }

    // TODO: Implement some sort of re-routing threshold
    if (!this.userIsNear(this.route.path[1])) {
      return;
    }

    // Remove the head and redraw the path
    this.route.path.shift();
    this.redrawPath(this.route.path);
  }
}

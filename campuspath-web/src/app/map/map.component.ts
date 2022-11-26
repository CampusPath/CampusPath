import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Map } from 'maplibre-gl';

import { GeoService } from '../geo-service.service';
import { Coords } from '../coords';
import { APIService } from '../api-service.service';
import { V1 } from '../search';

/*
TODO:
  Route service, 
    done kinda
  User position service, 
    done
  Check user position against route
    done
  Embedd this into the map
    uhhhhh
*/

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit, AfterViewInit, OnDestroy { 
  map: Map | undefined;

  //variables for routing
  route: V1.Route = {distance: 0, points: [[]]};
  dest: V1.Destination | undefined;
  userCoords: Coords = {lat: 0, lng: 0};
  counter: number = 0;

  //instance of the coords interface initially set to {0, 0}
  coords: Coords = {lat: 0, lng: 0};

  constructor(private geoService: GeoService, private apiService: APIService) { }

  @ViewChild('map')
  private mapContainer!: ElementRef<HTMLElement>;

  //overwrite the coords instance with getUserCoords on instantiation
  ngOnInit(): void{
    this.getUserCoords();
    this.getRoute();
  }

  //subscribe to the geoservice to set local coords variable to the user's actual coords
  getUserCoords(): void {
    this.geoService.getCoords().subscribe(coords => this.coords = coords);
  }
  getRoute(): void {
    this.apiService.getRoute().subscribe(route => this.route = route);
  }

  //display the map
  ngAfterViewInit() {
    const initialState = {lng: -95.252963, lat: 32.315867, zoom: 14};

    this.map = new Map({
      container: this.mapContainer.nativeElement,
      style: `https://api.maptiler.com/maps/streets/style.json?key=HfAVSwePVPgGxhY1Yvjw`,
      center: [initialState.lng, initialState.lat],
      zoom: initialState.zoom
    });

    this.map.on('data', () => {
      this?.map?.addSource('route', {
      'type': 'geojson',
      'data': {
        'type': 'Feature',
        'properties': {},
        'geometry': {
          'type': 'LineString',
          'coordinates': this.updateRoute()
        }
      }
    });

    this?.map?.addLayer({
      'id': 'route',
      'type': 'line',
      'source': 'route',
      'layout': {
        'line-join': 'round',
        'line-cap': 'round'
      },
      'paint': {
        'line-color': '#888',
        'line-width': 8
      }
    });
    });
  }

  //remove the map
  ngOnDestroy() {
    this.map?.remove();
  }

/*
    1. Check if the user is close enough to route[counter]
      a. if yes, "remove" route[counter] by ++counter
      b. else skip if statement
    2. Create a temp route variable
    3. Add the user as the first point
    4. Add all points after route[counter] from route
    5. Return the newly updated route as a number[][]

    NOTE counter will need to be reset whenever there is a new end destination
  */
    updateRoute(): number[][] {
      if(this.userIsNear(this.route?.points[this.counter][0],
        this.route?.points[this.counter][1])) {
          this.counter++;
      }
  
      let updatedPoints: number[][] = [];
      updatedPoints.push([this.userCoords.lat, this.userCoords.lng]);
  
      for(let i = this.counter; i < this.route.points.length; i++) {
        updatedPoints.push([this.route.points[i][0], this.route.points[i][1]]);
      }
  
      return updatedPoints;
    }
  
    //check if |user.lat - param lat| and |user.lng - param lng| are within some nearness threshold
    //this will need tweaking
    //0.0001 roughly equals 11.1 meters, so this will have about 22.2 meters leeway
    //might be too much or too little, itll have to be tested
    latThresh: number = 0.0001;
    lngThresh: number = 0.0001;
    userIsNear(lat: number, lng: number): boolean {
      let latDiff = Math.abs(this.userCoords.lat - lat);
      let lngDiff = Math.abs(this.userCoords.lng - lng);
  
      return ((latDiff <= this.latThresh) && (lngDiff <= this.lngThresh));
    }
}

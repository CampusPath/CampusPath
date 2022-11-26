import { Component, OnInit } from '@angular/core';
import { Map } from 'maplibre-gl';

import { V1 } from '../search';

import { GeoService } from '../geo-service.service';
import { APIService } from '../api-service.service';
import { Coords } from '../coords';


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
  selector: 'app-route',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.css']
})
export class RouteComponent implements OnInit {

  route: V1.Route = {distance: 0, points: [[]]};
  dest: V1.Destination | undefined;
  userCoords: Coords = {lat: 0, lng: 0};
  counter: number = 0;

  constructor(private geoService: GeoService, private apiService: APIService) { }

  ngOnInit(): void {
    this.getUserCoords();
    this.getRoute();
  }

  getUserCoords(): void {
    this.geoService.getCoords().subscribe(coords => this.userCoords = coords);
  }

  getRoute(): void {
    this.apiService.getRoute().subscribe(route => this.route = route);
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
  
  //function sets the route for a given map using "nodes" as coordinates
  //this might need to be called repeatedly whenever its actually embedded so that the route updates, not sure yet
  setRoute(map: Map) {
    map.addSource('route', {
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

    map.addLayer({
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
  }
}

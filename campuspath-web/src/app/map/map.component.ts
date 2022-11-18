import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Map } from 'maplibre-gl';

import { GeoService } from '../geo-service.service';
import { Coords } from '../coords';


@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit, AfterViewInit, OnDestroy { 
  map: Map | undefined;

  //instance of the coords interface initially set to {0, 0}
  coords: Coords = {lat: 0, lng: 0};

  constructor(private geoService: GeoService) { }

  @ViewChild('map')
  private mapContainer!: ElementRef<HTMLElement>;

  //overwrite the coords instance with getUserCoords on instantiation
  ngOnInit(): void{
    this.getUserCoords();
  }

  //subscribe to the geoservice to set local coords variable to the user's actual coords
  getUserCoords(): void {
    this.geoService.getCoords().subscribe(coords => this.coords = coords);
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

  }

  //remove the map
  ngOnDestroy() {
    this.map?.remove();
  }

}

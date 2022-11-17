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

  coords: Coords = {lat: 0, lng: 0};

  constructor(private geoService: GeoService) { }

  @ViewChild('map')
  private mapContainer!: ElementRef<HTMLElement>;

  ngOnInit(): void{
    this.getUserCoords();
  }

  getUserCoords(): void {
    this.geoService.getCoords().subscribe(coords => this.coords = coords);
  }

  //@ZEROMEMES replace the string below after "key=" with your key
  ngAfterViewInit() {
    const initialState = {lng: -95.252963, lat: 32.315867, zoom: 14};

    this.map = new Map({
      container: this.mapContainer.nativeElement,
      style: `https://api.maptiler.com/maps/streets/style.json?key=HfAVSwePVPgGxhY1Yvjw`,
      center: [initialState.lng, initialState.lat],
      zoom: initialState.zoom
    });


  }


  ngOnDestroy() {
    this.map?.remove();
  }

}

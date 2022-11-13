import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Map } from 'maplibre-gl';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit, AfterViewInit, OnDestroy {
  map: Map | undefined;

  @ViewChild('map')
  private mapContainer!: ElementRef<HTMLElement>;

  location: Observable<any>;
  constructor(private geoService: GeoService) {
  }
  

  ngOnInit(): void {
    this.location = this.geoService.geoLocation$.pipe(
      tap(console.log)
    );

    this.geoService.getUserLocation();

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

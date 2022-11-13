import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Map } from 'maplibre-gl';
//import { GeoService } from './geo-service.service';
import { GeoService } from './geo-service.service';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit, AfterViewInit, OnDestroy {
  map: Map | undefined;
  location!: Observable<any>;

  constructor(private geoService: GeoService) {
  }


  @ViewChild('map')
  private mapContainer!: ElementRef<HTMLElement>;




  ngOnInit(): void {


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

    this.location = this.geoService.geoLocation$.pipe(
      tap(console.log),
    );

    this.geoService.getUserLocation();



  }

  ngOnDestroy() {
    this.map?.remove();
  }

}

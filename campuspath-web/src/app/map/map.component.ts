import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Map } from 'maplibre-gl';
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
  @ViewChild('search1') search1!: ElementRef<HTMLElement>;





  ngOnInit(): void{



    this.location = this.geoService.geoLocation$.pipe(
      tap(console.log),
    );

    this.geoService.getUserLocation();
    console.log ();





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

  sendUserQuery($event: any){

    const q = document.getElementById('search1');


    console.log("User Searched : " + q?.nodeValue);
   // console.log("User 2 Searched: " + this.search1.nativeElement);


  }
  getValue(event: Event): string {
    return (event.target as HTMLInputElement).value;
  }

}

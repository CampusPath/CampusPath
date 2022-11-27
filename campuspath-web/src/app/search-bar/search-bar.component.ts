import { Component, OnInit } from '@angular/core';
import { APIService } from '../api-service.service';

import { Observable, Subject } from 'rxjs';
import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';

import { V1 } from '../search';

import { environment } from 'src/environments/environment';
import { Coords } from '../coords';


@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {
  //Variable that stores the text the user enters into the search bar
  destination$!: Observable<V1.Destination[]>;
  private searchTerms = new Subject<string>();
  userLat = Coords.lat;
  userLng = Coords.lng;

  constructor(private apiService: APIService) {}

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.destination$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.apiService.search(environment.defaultCampusID, term)),
    );
  }

/*
TODO: Take building label and grab coordinates and send it to api service to get back list of nodes and assign it to 2d array 
for map component
*/
  sendBuildingLabel(buildingId){
    //this.apiService.route(this.userLat, this.userLng, buildingId).subscribe();


  }

}

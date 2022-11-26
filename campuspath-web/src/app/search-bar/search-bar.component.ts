import { Component, OnInit, Input } from '@angular/core';
import { APIService } from '../api-service.service';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {
  //Variable that stores the text the user enters into the search bar
  public searchText: string = "";
  destination$: Observable<Destination[]>;
  private searchTerms = new Subject<string>();

  constructor(private apiService: ApiService) {}

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
      switchMap((term: string) => this.apiService.search(term)),
    );

  }

  //method bound to the search button. 
/*
  search() {
    console.log(this.searchText);
    SEARCH.search = this.searchText;
  }
  */
}

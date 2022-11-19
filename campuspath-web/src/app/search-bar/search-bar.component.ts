import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {
  //Variable that stores the text the user enters into the search bar
  public searchText: string = "";

  constructor() {}

  ngOnInit(): void {

  }

  //method bound to the search button. 
  search() {
    console.log(this.searchText);
  }
}

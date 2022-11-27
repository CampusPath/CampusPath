import { Component, Input} from '@angular/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

//https://ng-bootstrap.github.io/#/components/modal/api

@Component({
  selector: 'app-search-menu',
  templateUrl: './search-menu.component.html',
  styleUrls: ['./search-menu.component.css']
})
export class SearchMenuComponent {
  @Input() buildingLabel: String = "";

  constructor(public activeModal: NgbActiveModal) { }

  goButton() {
    console.log("Go button pressed " + this.buildingLabel);
  }
}

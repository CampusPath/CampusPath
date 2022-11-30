import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-arrival-popup',
  templateUrl: './arrival-popup.component.html',
  styleUrls: ['./arrival-popup.component.css']
})
export class ArrivalPopupComponent {
  @Input() destName: String ='';

  constructor(public activeModal: NgbActiveModal) {

  }
}

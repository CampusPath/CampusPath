import { AfterViewInit, Component, ElementRef, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { APIService } from '../api-service.service';

import { Observable, Subject } from 'rxjs';
import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';

import { V1 } from '../search';

import { environment } from 'src/environments/environment';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements AfterViewInit {
  //Variable that stores the text the user enters into the search bar
  destination$!: Observable<V1.Destination[]>;
  private searchTerms = new Subject<string>();

  @ViewChild('searchBox')
  private searchBox!: ElementRef<HTMLInputElement>;

  @Output() routeEvent = new EventEmitter<string>();

  constructor(private apiService: APIService, private modalService: NgbModal) {}

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngAfterViewInit(): void {
    this.initSearchService();
  }

  open(dest: V1.Destination) {
    const modalRef = this.modalService.open(SearchModalContent);
    modalRef.componentInstance.destName = dest.name;
    modalRef.componentInstance.onGoPressed.subscribe(
      () => {
        this.routeTo(dest);
      }
    );
  }

  routeTo(dest: V1.Destination): void {
    this.initSearchService();
    this.routeEvent.emit(dest.id);
  }

  initSearchService() {
    this.searchBox.nativeElement.value = '';
    this.destination$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.apiService.search(environment.defaultCampusID, term)),
    );
  }
}

// FIXME: this should probably be moved to a different class, but its here for right now cause uhhh dont worry about it
@Component({
  selector: 'search-modal-content',
  templateUrl:'./search-modal-content.html',
})
export class SearchModalContent {
  @Input() destName: String = '';
  @Output() onGoPressed: EventEmitter<any> = new EventEmitter();

  constructor(public activeModal: NgbActiveModal) {}

  goPressed() {
    this.onGoPressed.emit();
    this.activeModal.dismiss('Go click');
  }
}

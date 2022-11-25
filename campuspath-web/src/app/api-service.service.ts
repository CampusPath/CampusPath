import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { MessageService } from './message.service';
import { Nodes, Search } from './search';

@Injectable({ providedIn: 'root' })
export class APIService {

  constructor (private http: HttpClient,
    private messageService: MessageService){}

  promptBuildingNames(search: string){

    return this.http.get<Search[]>(`${environment.campusPathURL}/?search=${search}`);
    /*
    .pipe(
      tap(_ => this.log(`found buildings matching "${search}"`)),
      catchError(this.handleError<Search[]>('promptBuildingNames', ))
    );
    */
  }

  promptNodeList(lat: string, lng: string, destination: string){

    return this.http.get<Nodes[]>(`${environment.campusPathURL}/?route=${lat},${lng},${destination}`);

  }


  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
   private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string) {
    this.messageService.add(`APIService: ${message}`);
  }

}

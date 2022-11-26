import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { MessageService } from './message.service';
import { V1 } from './search';

@Injectable({providedIn: 'root'})
export class APIService {

  constructor(private http: HttpClient,
              private messageService: MessageService) {
  }

  /**
   * Wrapper around the "Campuses" endpoint for API V1
   */
  listCampuses() {
    return this.http.get<V1.Campus[]>(`${environment.apiURL}/v1/campuses`);
  }

  /**
   * Wrapper around the "Campus" endpoint for API V1
   *
   * @param campusId The UUID of the campus to retrieve the info for
   */
  getCampus(campusId: string) {
    return this.http.get<V1.Campus>(`${environment.apiURL}/v1/campus/${campusId}`);
  }

  /**
   * Wrapper around the "Search" endpoint for API V1
   *
   * @param campusId The UUID of the campus to search
   * @param query    A user query string for a destination
   */
  promptBuildingNames(campusId: string, query: string) {
    return this.http.get<V1.Destination[]>(`${environment.apiURL}/v1/search/${campusId}?q=${query}`);
    /*
    .pipe(
      tap(_ => this.log(`found buildings matching "${search}"`)),
      catchError(this.handleError<Search[]>('promptBuildingNames', ))
    );
    */
  }

  /**
   * Wrapper around the "Route" endpoint for API V1
   *
   * @param lat The user's current latitude
   * @param lng The user's current longitude
   * @param destination The UUID of the desired destination
   */
  promptNodeList(lat: number, lng: number, destination: string) {
    return this.http.post<V1.Route>(`${environment.apiURL}/v1/route`, {
      location: [lat, lng],
      destination: destination
    });
  }

  /**
   * Handle an HTTP operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
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

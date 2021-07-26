import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Feature } from '../dtos/feature';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FeatureService {
  private baseUrl = environment.baseUrl + 'feature/'
  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  getFeatures(): Observable<Feature[]> {
    return this.httpClient.get<Feature[]>(this.baseUrl)
      .pipe(
        retry(2),
        catchError(this.handleError));
  }

  saveFeature(permition: Feature): Observable<Feature> {
    return this.httpClient.post<Feature>(this.baseUrl, JSON.stringify(permition), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      );
  }
  updateFeature(permition: Feature): Observable<Feature> {
    return this.httpClient.put<Feature>(this.baseUrl, JSON.stringify(permition), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      );
  }
  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Código do erro: ${error.status},` + ` messagem: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
}

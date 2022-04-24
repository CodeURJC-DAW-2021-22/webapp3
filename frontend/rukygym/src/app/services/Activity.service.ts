import { Activity } from './../models/Activity.model';
import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

const BASE_URL = '/api/group-activities/';

@Injectable({ providedIn: 'root' })
export class BooksService {

  constructor(private httpClient: HttpClient) { }

  // Get all group activities
  getActivities(): Observable<Activity[]>{
    return this.httpClient.get(BASE_URL).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Activity[]>;
  }

  // Get activity by id
  getActivity(id: number | string): Observable<Activity> {
		return this.httpClient.get(BASE_URL + id).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Activity>;
	}

  // Falta buscar activity por monitor id?

  // Delete activity
  deleteActivity(activity: Activity) {
		return this.httpClient.delete(BASE_URL + activity.id).pipe(
			catchError(error => this.handleError(error))
		);
	}

  // Add activity
  addActivity(activity: Activity) {
		if (!activity.id) {
			return this.httpClient.post(BASE_URL, activity)
				.pipe(
					catchError(error => this.handleError(error))
				);
		} else {
			return this.httpClient.put(BASE_URL + activity.id, activity).pipe(
				catchError(error => this.handleError(error))
			);
		}
	}

  // Update activity
  updateActivity(activity: Activity) {
		return this.httpClient.put(BASE_URL + activity.id, activity).pipe(
			catchError(error => this.handleError(error))
		);
	}

  // Error handler
  private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
    // dice que esta deprecated
		return throwError("Server error (" + error.status + "): " + error.text())
    // mejor esto?
    //return new Error("Server error (" + error.status + "): " + error.text())
	}
}

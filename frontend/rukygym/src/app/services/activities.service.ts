import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Activity } from "../models/Activity.model";

const BASE_URL = '/api/group-activities/';

//https://codeurjc-daw-2021-22-webapp3.herokuapp.com/api/group-activities/

@Injectable({ providedIn: 'root' })
export class ActivityService {

  constructor(private httpClient: HttpClient) { }

  // Get all group activities
  getActivities() {
    return this.httpClient.get(BASE_URL).pipe(
			catchError(error => this.handleError(error))
		);
  }

  // Get activity by id
  getActivity(id: number | string) {
		return this.httpClient.get(BASE_URL + id).pipe(
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
    return this.httpClient.put(BASE_URL, activity).pipe(
		catchError(error => this.handleError(error))
	)
  }

  // Delete activity
  deleteActivity(activity: Activity) {
		return this.httpClient.delete(BASE_URL + activity.id).pipe(
			catchError(error => this.handleError(error))
		);
	}

  // Set Activity Image
  setActivityImage (activity: Activity, formData: FormData){
		return this.httpClient.put(BASE_URL + activity.id + '/image', formData)
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

  // Delete Activity Image
  deleteActivityImage(activity: Activity) {
		return this.httpClient.delete(BASE_URL + activity.id + '/image')
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

  // Error handler
  private handleError(error: any): any  {
		console.log("ERROR:");
		console.error(error);
    return throwError("Server error (" + error.status + "): " + error.text())
	}
}
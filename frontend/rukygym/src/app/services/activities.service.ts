import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs';
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

  getActivityImage(activity: Activity) {
    return this.httpClient.get(BASE_URL + activity.id + '/image')
        .pipe(
            catchError(error => this.handleError(error))
        );
}


  // Error handler
  private handleError(error: any): any  {
		console.log("ERROR:");
		console.log(error);
        return throwError("Server error (" + error.status + "): " + error)
	}
}
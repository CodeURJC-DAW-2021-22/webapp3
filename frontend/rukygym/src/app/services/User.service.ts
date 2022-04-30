import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from './../models/User.model';

const BASE_URL = '/api/users/';

@Injectable({ providedIn: 'root' })
export class UserService {

  constructor(private httpClient: HttpClient) { }

  // Monitors

  // Get all monitors
  getMonitors(){
    return this.httpClient.get(BASE_URL + 'monitors').pipe(
			catchError(error => this.handleError(error))
		);
  }

  // Get monitor by id
  getMonitor(id: number | string){
		return this.httpClient.get(BASE_URL + 'monitors/' + id).pipe(
			catchError(error => this.handleError(error))
		);
	}

  // Add monitor
  addMonitor(monitor: User) {
		if (!monitor.id) {
			return this.httpClient.post(BASE_URL + 'monitors/new/', monitor)
				.pipe(
					catchError(error => this.handleError(error))
				);
		} else {
			return this.httpClient.put(BASE_URL + 'monitors/' + monitor.id, monitor).pipe(
				catchError(error => this.handleError(error))
			);
		}
	}

  // Set monitor Image
  setMonitorImage(monitor: User, formData: FormData) {
		return this.httpClient.post(BASE_URL + 'monitor/' + monitor.id + '/image/', formData)
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

  // Update monitor
  updateMonitor(monitor: User) {
		return this.httpClient.put(BASE_URL + 'monitor/' + monitor.id + '/', monitor).pipe(
			catchError(error => this.handleError(error))
		);
	}

  // Delete monitor
  deleteExercise(monitor: User) {
		return this.httpClient.delete(BASE_URL + 'monitor/' + monitor.id).pipe(
			catchError(error => this.handleError(error))
		);
	}

  // Members



  // Error handler
  private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}
}
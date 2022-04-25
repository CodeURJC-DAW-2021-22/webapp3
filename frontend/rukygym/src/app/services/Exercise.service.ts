import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Exercise } from './../models/Exercise.model';

const BASE_URL = '/api/exercises/';

@Injectable({ providedIn: 'root' })
export class BooksService {

  constructor(private httpClient: HttpClient) { }

  // Get all Exercise
  getExercises(){
    return this.httpClient.get(BASE_URL).pipe(
			catchError(error => this.handleError(error))
		);
  }

  // Get Exercise by id
  getExercise(id: number | string){
		return this.httpClient.get(BASE_URL + id).pipe(
			catchError(error => this.handleError(error))
		);
	}

  // Delete Exercise
  deleteExercise(exercise: Exercise) {
		return this.httpClient.delete(BASE_URL + exercise.id).pipe(
			catchError(error => this.handleError(error))
		);
	}

  // Add Exercise
  addExercise(exercise: Exercise) {
		if (!exercise.id) {
			return this.httpClient.post(BASE_URL, exercise)
				.pipe(
					catchError(error => this.handleError(error))
				);
		} else {
			return this.httpClient.put(BASE_URL + exercise.id, exercise).pipe(
				catchError(error => this.handleError(error))
			);
		}
	}

  // Update Exercise
  updateExercise(exercise: Exercise) {
		return this.httpClient.put(BASE_URL + exercise.id, exercise).pipe(
			catchError(error => this.handleError(error))
		);
	}

  // find page??

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

import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Exercise } from './../models/Exercise.model';

const BASE_URL = '/api/exercises/';

@Injectable({ providedIn: 'root' })
export class ExerciseService {

  constructor(private httpClient: HttpClient) { }

  // Get all Exercise
  getExercises(id : number){
    return this.httpClient.get(BASE_URL + '?page=' + id).pipe(

			catchError(error => this.handleError(error))
		);
  }

  // Get Exercise by id
  getExercise(id: number | string){
		return this.httpClient.get(BASE_URL + id).pipe(
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

  // Delete Exercise
  /*deleteExercise(exercise: Exercise) {
		return this.httpClient.delete(BASE_URL + exercise.id).pipe(
			catchError(error => this.handleError(error))
		);
	}*/

  // Set Exercise Image
  setExerciseImage(exercise: Exercise, formData: FormData) {
		return this.httpClient.post(BASE_URL + exercise.id + '/image', formData)
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

  // Delete Exercise Image
  deleteExerciseImage(exercise: Exercise) {
		return this.httpClient.delete(BASE_URL + exercise.id + '/image')
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

  // Error handler
  private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}
}

import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ExerciseTable } from './../models/ExerciseTable.model';

const BASE_URL = '/api/exercises-tables/';

@Injectable({ providedIn: 'root' })
export class ExerciseTableService {

  constructor(private httpClient: HttpClient) { }

  // Get all Exercise Table
  getExercisesTables(){
    return this.httpClient.get(BASE_URL).pipe(
			catchError(error => this.handleError(error))
		);
  }

  // Get Exercise table by id
  getExerciseTable(id: number | string){
		return this.httpClient.get(BASE_URL + id).pipe(
			catchError(error => this.handleError(error))
		);
	}

  // Add Exercise table
  addExerciseTable(exerciseTable: ExerciseTable) {
		if (!exerciseTable.id) {
			return this.httpClient.post(BASE_URL, exerciseTable)
				.pipe(
					catchError(error => this.handleError(error))
				);
		} else {
			return this.httpClient.put(BASE_URL + exerciseTable.id, exerciseTable).pipe(
				catchError(error => this.handleError(error))
			);
		}
	}

  // Update Exercise table
  updateExerciseTable(exerciseTable: ExerciseTable) {
		return this.httpClient.put(BASE_URL + exerciseTable.id, exerciseTable).pipe(
			catchError(error => this.handleError(error))
		);
	}

  // Delete Exercise
  /*deleteExercise(exercise: Exercise) {
		return this.httpClient.delete(BASE_URL + exercise.id).pipe(
			catchError(error => this.handleError(error))
		);
	}*/

  // Set Exercise table Image
  setExerciseTableImage(exerciseTable: ExerciseTable, formData: FormData) {
		return this.httpClient.post(BASE_URL + exerciseTable.id + '/image', formData)
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

  // Delete Exercise table Image
  deleteExerciseTableImage(exerciseTable: ExerciseTable) {
		return this.httpClient.delete(BASE_URL + exerciseTable.id + '/image')
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

  // puede que falte / al antes de pdf
  // Get Exercise Table pdf
  getExerciseTablePDF(id: number | string){
		return this.httpClient.get(BASE_URL + id + 'pdf').pipe(
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

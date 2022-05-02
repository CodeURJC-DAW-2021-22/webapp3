import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from './../models/User.model';

const BASE_URL = '/api/users/';

@Injectable({ providedIn: 'root' })
export class StatisticsService {

    constructor(private httpClient: HttpClient) { }


    getAdminStats() {
        return this.httpClient.get(BASE_URL + 'admin/statistics').pipe(
			catchError(error => this.handleError(error))
		);
    }

    getUserStats() {
        return this.httpClient.get(BASE_URL + 'members/statistics').pipe(
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
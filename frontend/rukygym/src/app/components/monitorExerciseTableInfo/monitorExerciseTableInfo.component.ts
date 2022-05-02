import { Exercise } from './../../models/Exercise.model';
import { Component } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ExerciseTable } from "src/app/models/ExerciseTable.model";
import { ExerciseTableService } from "src/app/services/ExerciseTable.service";
import { LoginService } from "src/app/services/Login.service";

@Component({
    selector:'monitorExerciseTableInfo',
    templateUrl: './monitorExerciseTableInfo.component.html',
    styleUrls: ['./monitorExerciseTableInfo.component.css']
})
export class MonitorExerciseTableInfo{

  exerciseTables: ExerciseTable | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ExerciseTableService,
      public loginService: LoginService) {

      const id = activatedRoute.snapshot.params['id'];
      service.getExerciseTable(id).subscribe(
          exerciseTables => this.exerciseTables = exerciseTables as ExerciseTable,
          error => console.error(error),
      );
  }

}

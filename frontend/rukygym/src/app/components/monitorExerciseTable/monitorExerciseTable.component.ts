import { ExerciseTableService } from './../../services/ExerciseTable.service';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ExerciseTable } from '../../models/ExerciseTable.model';

@Component({
  selector: 'monitorExerciseTable',
  templateUrl: './monitorExerciseTable.component.html',
  styleUrls: ['./monitorExerciseTable.component.css']
})

export class MonitorExerciseTable {

  exerciseTables : ExerciseTable [] | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ExerciseTableService,
      /*public loginService: LoginService*/) {

      service.getExercisesTables().subscribe(
        exerciseTables  => this.exerciseTables = exerciseTables as ExerciseTable [],
          (error: any)                => console.error(error),
      );

      console.log(this.exerciseTables);
  }


}

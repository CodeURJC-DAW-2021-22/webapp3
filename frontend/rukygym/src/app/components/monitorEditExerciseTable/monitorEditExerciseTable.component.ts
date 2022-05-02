import { ExerciseTableService } from './../../services/ExerciseTable.service';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ExerciseTable } from '../../models/ExerciseTable.model';
import { LoginService } from 'src/app/services/Login.service';

@Component({
  selector: 'monitorEditExerciseTable',
  templateUrl: './monitorEditExerciseTable.component.html',
  styleUrls: ['./monitorEditExerciseTable.component.css']
})

export class MonitorEditExerciseTable {

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

import { Component } from "@angular/core"
import { Router, ActivatedRoute } from "@angular/router";
import { ExerciseTable } from 'src/app/models/ExerciseTable.model';
import { ExerciseTableService } from 'src/app/services/ExerciseTable.service';

@Component({
  selector: 'exercisetables',
  templateUrl: './exercisetables.component.html',
  styleUrls: ['./exercisetables.component.css']
})
export class ExercisetablesComponent {

  exerciseTabs : ExerciseTable [] | undefined;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ExerciseTableService,
        /*public loginService: LoginService*/) {

        service.getExercisesTables().subscribe(
            exerciseTabs  => this.exerciseTabs = exerciseTabs as ExerciseTable [],
            (error: any)                => console.error(error),
        );

        console.log(this.exerciseTabs);
    }

}

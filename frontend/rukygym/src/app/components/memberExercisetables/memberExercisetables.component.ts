import { Component } from "@angular/core"
import { Router, ActivatedRoute } from "@angular/router";
import { ExerciseTable } from 'src/app/models/ExerciseTable.model';
import { ExerciseTableService } from 'src/app/services/ExerciseTable.service';
import { LoginService } from "src/app/services/login.service";

@Component({
  selector: 'memberexercisetables',
  templateUrl: './memberExercisetables.component.html',
  styleUrls: ['./memberExercisetables.component.css']
})
export class MemberExercisetablesComponent {

  page = 0;
  exerciseTables : ExerciseTable [] | undefined;
  show: boolean = true;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ExerciseTableService) {

      service.getExercisesTables(0).subscribe(
        exerciseTables  => this.exerciseTables = exerciseTables as ExerciseTable [],
          (error: any)                => console.error(error),
      );

      console.log(this.exerciseTables);
  }

  more() {
    this.page++;
        this.service.getExercisesTables(this.page).subscribe(
            x => {
                for (let e of x as ExerciseTable [])
                        this.exerciseTables?.push(e);
                if ((x as ExerciseTable []).length != 8) {
                    this.show = false;
                }
            },
            error => alert("No fue posible cargar los clientes del servidor. Inténtelo más tarde.")
        )
  }

}





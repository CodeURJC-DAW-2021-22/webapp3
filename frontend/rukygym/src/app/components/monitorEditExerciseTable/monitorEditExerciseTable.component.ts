import { ExerciseTable } from 'src/app/models/ExerciseTable.model';
import { ExerciseTableService } from './../../services/ExerciseTable.service';
import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ExerciseService } from 'src/app/services/Exercise.service';
import { Exercise } from 'src/app/models/Exercise.model';



@Component({
  selector: 'monitorEditExerciseTable',
  templateUrl: './monitorEditExerciseTable.component.html',
  styleUrls: ['./monitorEditExerciseTable.component.css']
})

export class MonitorEditExerciseTable {


  @ViewChild("file")
  file: any;

  page = 0;
  show : boolean = true;
  exerciseTables: ExerciseTable = {
    name: "",
    description: "",
    exercises: []
  };
  exercise : Exercise[] = [];
  ids: Exercise[] = [];

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ExerciseTableService, private exerciseService: ExerciseService) {

      const id = activatedRoute.snapshot.params['id'];
      service.getExerciseTable(id).subscribe(
          exerciseTables => this.exerciseTables = exerciseTables as ExerciseTable,
          error => console.error(error),
      );

      exerciseService.getExercises(0).subscribe(
        exercise => this.exercise = exercise as Exercise [],
        error => alert("No fue posible cargar los ejercicios del servidor. Inténtelo más tarde.")
      );

  }

  save() {
    this.exerciseTables.exercises = this.ids;
    this.service.updateExerciseTable(this.exerciseTables as ExerciseTable).subscribe(
        act => this.uploadImg(act),
        error => alert('Error updating exercise table: ' + error)
    );

    this.router.navigate(["new/monitorExerciseTable" + this.exerciseTables?.id]);
}

uploadImg(ExerciseTable: unknown): void {
  const image = this.file.nativeElement.files[0];

  if (image) {
      let formData = new FormData();
      formData.append("imageFile", image);
      this.service.setExerciseTableImage( ExerciseTable as ExerciseTable, formData).subscribe(
          _ => _,
          error => alert('Error uploading activity image: ' + error)
      );
  }

  this.router.navigate(["new/monitorExerciseTable"]);
}

more() {
  this.page++;
  this.exerciseService.getExercises(this.page).subscribe(
      clients => {
          for (let e of clients as Exercise [])
                  this.exercise?.push(e);
          if ((clients as Exercise []).length != 10) {
              this.show = false;
          }
      },
      _ => alert("No fue posible cargar los clientes del servidor. Inténtelo más tarde.")
  )
}

select(exercise: Exercise | undefined) {
  if (this.ids.includes(exercise as Exercise))
      this.ids = this.ids.filter(e => e != exercise);
  else
      this.ids.push(exercise as Exercise);
}


}

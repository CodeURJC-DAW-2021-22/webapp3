import { ExerciseTableService } from './../../services/ExerciseTable.service';
import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ExerciseTable } from '../../models/ExerciseTable.model';
import { Exercise } from 'src/app/models/Exercise.model';
import { ExerciseService } from 'src/app/services/Exercise.service';

@Component({
  selector: 'monitorAddExerciseTable',
  templateUrl: './monitorAddExerciseTable.component.html',
  styleUrls: ['./monitorAddExerciseTable.component.css']
})

export class MonitorAddExerciseTable {

  page = 0;
  exerciseTables: ExerciseTable = {
    name: "",
    description: "",
    exercises: []
  };
  show : boolean = true;
  ids: Exercise[] = [];
  exercise : Exercise[] = [];

  @ViewChild("file")
  file: any;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ExerciseService, private serv : ExerciseTableService) {

      service.getExercises(0).subscribe(
        exercise => this.exercise = exercise as Exercise [],
        error => alert("No fue posible cargar los ejercicios del servidor. Inténtelo más tarde.")
      );
  }

  more() {
    this.page++;
    this.service.getExercises(this.page).subscribe(
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

  save() {
    this.exerciseTables.exercises = this.ids;
    this.serv.addExerciseTable(this.exerciseTables).subscribe(
      x => this.updateImg(x as ExerciseTable),
      _ => alert("No se ha podido guardar la lista. Inténtelo mas tarde.")
    )       
  }

  exit() {
    window.location.href = '/new/monitorExerciseTable'
  }

  updateImg(monitor: ExerciseTable): void {
    const image = this.file.nativeElement.files[0];

    if (image) {
        let formData = new FormData();
        formData.append("imageFile", image);
        this.serv.setExerciseTableImage(monitor, formData).subscribe(
            _ => { 
                this.router.navigate(["/new/monitorExerciseTable"]); 
            },
            error => alert('Error uploading monitor image: ' + error)
        );
    }
  }

}

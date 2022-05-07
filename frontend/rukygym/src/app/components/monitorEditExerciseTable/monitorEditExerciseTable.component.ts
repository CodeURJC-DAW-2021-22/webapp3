import { ExerciseTable } from 'src/app/models/ExerciseTable.model';
import { ExerciseTableService } from './../../services/ExerciseTable.service';
import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';



@Component({
  selector: 'monitorEditExerciseTable',
  templateUrl: './monitorEditExerciseTable.component.html',
  styleUrls: ['./monitorEditExerciseTable.component.css']
})

export class MonitorEditExerciseTable {


  @ViewChild("file")
  file: any;

  exerciseTables: ExerciseTable | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ExerciseTableService) {

      const id = activatedRoute.snapshot.params['id'];
      service.getExerciseTable(id).subscribe(
          exerciseTables => this.exerciseTables = exerciseTables as ExerciseTable,
          error => console.error(error),
      );
  }

  save() {
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


}

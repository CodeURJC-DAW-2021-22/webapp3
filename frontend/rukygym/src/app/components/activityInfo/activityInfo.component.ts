import { Component } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Activity } from "src/app/models/Activity.model";
import { ActivityService } from "src/app/services/Activity.service";


@Component({
    selector:'activityinfo',
    templateUrl: './activityInfo.component.html',
    styleUrls: ['../../../assets/css/main.css']
})
export class ActivityInfo{

  activity: Activity | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ActivityService) {

      const id = activatedRoute.snapshot.params['id'];
      service.getActivity(id).subscribe(
          activity => this.activity = activity as Activity,
          error => console.error(error),
      );
  }

}

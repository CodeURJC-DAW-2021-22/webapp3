import { Component } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { LoginService } from "src/app/services/login.service";
import { Activity } from 'src/app/models/Activity.model';
import { ActivityService } from 'src/app/services/Activity.service';

@Component({
  selector: 'memberactivities',
  templateUrl: './memberActivities.component.html',
  styleUrls: ['./memberActivities.component.css']
})
export class ActivitiesComponent{

  activities : Activity [] | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ActivityService,
    public loginService: LoginService) {

      service.getActivities().subscribe(
        activities  => this.activities = activities as Activity [],
        (error: any)                => console.error(error),
    );

    console.log(this.activities);

    }

}

import { Component } from "@angular/core";

import { Router, ActivatedRoute } from "@angular/router";
import { Activity } from "../../models/Activity.model";

import { ActivityService } from "../../services/Activity.service";

@Component({
    selector:'activities',
    templateUrl: './activities.component.html',
    styleUrls: ['../../../assets/css/main.css']
})
export class ActivitiesComponent {

  activities : Activity [] | undefined;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ActivityService,
        /*public loginService: LoginService*/) {

        service.getActivities().subscribe(
            activities  => this.activities = activities as Activity [],
            (error: any)                => console.error(error),
        );

        console.log(this.activities);
    }

}

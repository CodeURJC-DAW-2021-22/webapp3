import { Component } from "@angular/core"
import { Router, ActivatedRoute } from "@angular/router";
import { Activity } from "src/app/models/activity.model";
import { ActivityService } from "src/app/services/activities.service";

@Component({
    selector:'adminactivities',
    templateUrl: './adminActivities.component.html',
    styleUrls: ['./adminActivities.component.css']
})

export class AdminActivities {

    activities : Activity [] = [];

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ActivityService, 
        /*public loginService: LoginService*/) {
            
        service.getActivities().subscribe(
            activities  => this.activities = activities as Activity [],
            (error: any)                => console.error(error),
        );

        console.log(this.activities);
    }

}
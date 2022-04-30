import { Component } from "@angular/core"
import { Router, ActivatedRoute } from "@angular/router";
import { ActivityService } from "src/app/services/Activity.service";
import { Activity } from "../../models/Activity.model";



@Component({
    selector:'monitoractivities',
    templateUrl: './monitorActivities.component.html',
    styleUrls: ['./monitorActivities.component.css']
})

export class MonitorActivities {

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

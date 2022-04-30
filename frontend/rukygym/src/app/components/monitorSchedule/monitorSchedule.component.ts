import { Component } from "@angular/core"
import { Router, ActivatedRoute } from "@angular/router";
import { ActivityService } from "src/app/services/Activity.service";
import { Activity } from "../../models/Activity.model";



@Component({
    selector:'monitorschedule',
    templateUrl: './monitorSchedule.component.html',
    styleUrls: ['./monitorSchedule.component.css']
})

export class MonitorSchedule {

    activities : Activity [] | undefined;

    // falta, ya que hay que sacar las actividades de un monitor
    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ActivityService,
        /*public loginService: LoginService*/) {

        service.getActivities().subscribe(
            activities  => this.activities = activities as Activity [],
            (error: any)                => console.error(error),
        );

        console.log(this.activities);
    }

}

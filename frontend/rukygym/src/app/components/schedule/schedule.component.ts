import { Component } from "@angular/core";

import { ActivatedRoute, Router } from "@angular/router";
import { Activity } from "src/app/models/Activity.model";
import { ActivityService } from "../../services/Activity.service";
import { LoginService } from "../../services/Login.service";



@Component({
    selector:'schedule',
    templateUrl: './schedule.component.html',
    styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent {

  activity: Activity | undefined;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ActivityService,
        public loginService: LoginService) {

        const id = activatedRoute.snapshot.params['id'];
        service.getActivity(id).subscribe(
            activity => this.activity = activity as Activity,
            error => console.error(error),
        );
    }


}

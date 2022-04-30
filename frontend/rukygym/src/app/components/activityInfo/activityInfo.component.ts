import { Component } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Activity } from "src/app/models/Activity.model";


@Component({
    selector:'activityinfo',
    templateUrl: './activityInfo.component.html',
    styleUrls: ['./activityInfo.component.css']
})
export class ActivityInfo{

/*    private activity: Activity;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ActivitiesService,
        public loginService: LoginService) {

        const id = activatedRoute.snapshot.params['id'];
        service.getActivity(id).subscribe(
            activity => this.activity = activity,
            error => console.error(error),
        );
    }*/


}

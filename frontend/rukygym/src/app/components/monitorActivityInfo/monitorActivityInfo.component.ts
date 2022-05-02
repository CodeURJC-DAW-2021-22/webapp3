import { Component } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { Activity } from "src/app/models/Activity.model";
import { ActivityService } from "src/app/services/Activity.service";

@Component({
    selector: 'monitoractivityinfo',
    templateUrl: './monitorActivityInfo.component.html',
    styleUrls: ['./monitorActivityInfo.component.css']
})

export class MonitorActivityInfo {

    activity: Activity | undefined;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ActivityService) {

        const id = activatedRoute.snapshot.params['id'];
        service.getActivity(id).subscribe(
            activity => this.activity = activity as Activity,
            error => console.error(error),
        );
    }
}
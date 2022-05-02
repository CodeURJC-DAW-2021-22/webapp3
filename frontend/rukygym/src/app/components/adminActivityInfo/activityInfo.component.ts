import { Component } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Activity } from "src/app/models/Activity.model";
import { ActivityService } from "src/app/services/activities.service";


@Component({
    selector:'activityinfo',
    templateUrl: './activityInfo.component.html',
    styleUrls: ['./activityInfo.component.css']
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

    delete(){
        this.service.deleteActivity(this.activity as Activity).subscribe(
            _ => alert("Deleted"),
            error => alert("Cannot be deleted")
        );
    }


            
    
}
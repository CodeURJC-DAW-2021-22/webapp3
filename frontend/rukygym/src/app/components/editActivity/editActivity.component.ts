import { Component } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { Activity } from "src/app/models/Activity.model";
import { ActivityService } from "src/app/services/activities.service";
import { LoginService } from "src/app/services/login.service";

@Component({
    selector: 'EditActivity',
    templateUrl: './editActivity.component.html',
    styleUrls: ['./editActivity.component.css'],
})

export class EditActivity {

    activity: Activity | undefined;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ActivityService,
        public loginService: LoginService) {
            this.loginService.logOut();
        this.loginService.logIn("admin@admin.com", "admin");
        const id = activatedRoute.snapshot.params['id'];
        service.getActivity(id).subscribe(
            (activity) => this.activity = activity as Activity,
            (error: any)    => console.error(error)
        );
        
    }


    save() {
        //
        this.service.updateActivity(this.activity as Activity);
    }
}
import { Component } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { User } from "src/app/models/User.model";
import { UserService } from "src/app/services/User.service";

@Component({
    selector:'adminmonitors',
    templateUrl: './adminmonitors.component.html',
    styleUrls : ['./adminmonitors.component.css']
})
export class AdminMonitors {

    monitors : User [] | undefined;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService) {
                
        service.getMonitors().subscribe(
            monitors  => this.monitors = monitors as User [],
            (error: any)                => console.error(error),
        );
    }
}
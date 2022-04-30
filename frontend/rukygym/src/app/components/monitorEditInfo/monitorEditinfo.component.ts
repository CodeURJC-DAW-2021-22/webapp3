import { Component } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { User } from "src/app/models/User.model";
import { LoginService } from "src/app/services/login.service";
import { UserService } from "src/app/services/User.service";


@Component({
    selector: 'monitorEditInfo',
    templateUrl: './monitorEditInfo.component.html',
    styleUrls: ['monitorEditInfo.component.css']
})
export class MonitorEditInfo {

    monitor: User | undefined;
    name: string = "";

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService,
        public loginService: LoginService) {

        const id = activatedRoute.snapshot.params['id'];
        service.getMonitor(id).subscribe(
            (monitor) => this.monitor = monitor as User,
            (error: any)    => console.error(error)
        );
            
        this.name = this.monitor?.name as string;
    }

    save(){
        this.loginService.logIn("admin@admin.com", "admin");
        this.service.updateMonitor(this.monitor as User);
    }
}
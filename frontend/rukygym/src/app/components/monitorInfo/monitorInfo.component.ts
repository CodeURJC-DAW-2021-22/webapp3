import { Component } from '@angular/core'
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/User.model';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/User.service';

@Component({
    selector: 'monitorinfo',
    templateUrl: './monitorInfo.component.html',
    styleUrls: ['./monitorInfo.component.css',],
})
export class MonitorInfo {

    monitor: User | undefined;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService,
        public loginService: LoginService) {

        const id = activatedRoute.snapshot.params['id'];
        service.getMonitor(id).subscribe(
            (monitor) => this.monitor = monitor as User,
            (error: any)    => console.error(error)
        );
    }

    delete(){
        this.service.deleteMonitor(this.monitor as User);
    }
}
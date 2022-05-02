import { UserService } from './../../services/User.service';
import { Component } from "@angular/core";
import { User } from "src/app/models/User.model";
import { Router, ActivatedRoute } from "@angular/router";


@Component({
    selector:'mainPage',
    templateUrl: './mainPage.component.html',
    styleUrls: ['./mainPage.component.css']
})
export class MainPageComponent {

  monitors : User[] | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService,
    /*public loginService: LoginService*/){
      service.getMonitors().subscribe(
        monitors  => this.monitors = monitors as User [],
        (error: any)                => console.error(error),
      );

      
    }

}

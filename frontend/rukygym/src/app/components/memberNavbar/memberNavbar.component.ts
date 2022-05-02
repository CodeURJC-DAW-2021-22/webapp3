import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { LoginService } from "src/app/services/login.service";


@Component({
    selector:'membernavbar',
    templateUrl: './memberNavbar.component.html',
    styleUrls: ['./memberNavbar.component.css']
})
export class MemberNavbarComponent {


    constructor(private router: Router,private service: LoginService) {
        
    }

    logout(){
       this.service.logOut();
       
    }

}


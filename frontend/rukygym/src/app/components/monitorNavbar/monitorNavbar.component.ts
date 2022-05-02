import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { LoginService } from "src/app/services/login.service";

@Component({
    selector: 'monitornavbar',
    templateUrl: './monitorNavbar.component.html',
    styleUrls: ['./monitorNavbar.component.css']
})
export class MonitorNavbar {

    constructor(private router: Router,private service: LoginService) {
        
    }

    logout(){
       this.service.logOut();
       
    }
}
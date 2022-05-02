import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { LoginService } from "src/app/services/login.service";


@Component({
    selector:'adminnavbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
})
export class AdminNavbarComponent {

    constructor(private router: Router,private service: LoginService) {
        
    }

    logout(){
       this.service.logOut();
       
    }

}



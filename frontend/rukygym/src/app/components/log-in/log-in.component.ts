import { Component, EventEmitter, Output, ResolvedReflectiveFactory } from "@angular/core";
import { User } from "src/app/models/User.model";
import { LoginService } from "src/app/services/login.service";




@Component({
    selector:'log-in',
    templateUrl: './log-in.component.html',
    styleUrls: ['../../../assets/css/main.css']
})
export class LoginComponent {


  constructor(private loginService: LoginService) { }

  logIn(event: any, user: string, pass: string) {

    event.preventDefault();

    this.loginService.logIn(user, pass);


  }

  logOut() {
    this.loginService.logOut();
  }




}

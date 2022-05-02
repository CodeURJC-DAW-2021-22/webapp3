import { User } from './../../models/User.model';
import { UserService } from './../../services/User.service';
import { Component } from "@angular/core";


@Component({
    selector:'sign-in',
    templateUrl: './sign-in.component.html',
    styleUrls: ['./sign-in.component.css']
})
export class SigninComponent {

  constructor(public userService : UserService) {}

  signIn (name: string, surname: string, nif: string, phone: string, cp: string, address: string, email: string, password: string, height: string, weight: string, medicalInfo: string) {

    const user = {name : name, surname : surname, NIF : nif, phone : phone, postalCode : cp, address: address, email: email, height: height, weight:weight, medicalInfo:medicalInfo, userType: "monitor"} as User;

    this.userService.addMonitor(user);
  }

}

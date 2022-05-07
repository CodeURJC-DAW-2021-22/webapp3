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

    const user = {name : name, 
      encodedPassword: password,
      surname : surname, 
      NIF : nif, 
      phone : phone, 
      postalCode : cp, 
      address: address, 
      email: email, 
      height: height, 
      weight:weight, 
      medicalInfo:medicalInfo, 
      userType: "member"} as User;

    this.userService.addMembers(user).subscribe(
      _ => window.location.href = "/new/log-in",
      _ => _
    );
  }

}

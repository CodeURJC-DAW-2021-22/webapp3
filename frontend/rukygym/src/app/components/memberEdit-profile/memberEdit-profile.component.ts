import { Component } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { LoginService } from "src/app/services/login.service";
import { UserService } from 'src/app/services/User.service';
import { User } from 'src/app/models/User.model';

@Component({
  selector: 'membereditprofile',
  templateUrl: './memberEdit-profile.component.html',
  styleUrls: ['./memberEdit-profile.component.css']
})
export class EditProfileComponent {

    member: User | undefined;
    name: string = " ";

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService,
    public loginService: LoginService) {

      const id = activatedRoute.snapshot.params['id'];
      service.getMember(id).subscribe(
      (member) => this.member = member as User,
      (error: any)    => console.error(error)
    );

    this.name = this.member?.name as string;

    }

    save(){
      this.loginService.logIn("suuu@gmail.com", "password");
      this.service.updateMonitor(this.member as User);
    }

}

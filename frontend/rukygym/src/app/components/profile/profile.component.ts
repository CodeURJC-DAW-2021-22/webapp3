import { Component } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { LoginService } from "src/app/services/Login.service";
import { UserService } from 'src/app/services/User.service';
import { User } from 'src/app/models/User.model';


@Component({
  selector: 'profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  member: User | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService,
    public loginService: LoginService) {

      const id = activatedRoute.snapshot.params['id'];
        service.getMember(id).subscribe(
            (member) => this.member = member as User,
            (error: any)    => console.error(error)
        );


    }
}

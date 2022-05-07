import { Component } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { UserService } from 'src/app/services/User.service';
import { User } from 'src/app/models/User.model';
import { LoginService } from "src/app/services/login.service";



@Component({
  selector: 'memberprofile',
  templateUrl: './memberProfile.component.html',
  styleUrls: ['./memberProfile.component.css']
})
export class MemberProfileComponent {

  member: User | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService,
    public loginService: LoginService) {

      const id = activatedRoute.snapshot.params['id'];
        service.getMember(id).subscribe(
            (member) => this.member = member as User,
            (error: any)    => console.error(error)
        );


      if (this.loginService.currentUser() === undefined){
        console.error("no hay usuario");
      } else{
        this.loginService.currentUser()?.id;
      }


    }
}

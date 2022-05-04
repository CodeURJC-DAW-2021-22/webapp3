import { Component, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { LoginService } from "src/app/services/login.service";
import { UserService } from 'src/app/services/User.service';
import { User } from 'src/app/models/User.model';

@Component({
  selector: 'membereditprofile',
  templateUrl: './memberEdit-profile.component.html',
  styleUrls: ['./memberEdit-profile.component.css']
})
export class MemberEditProfileComponent {

    member: User | undefined;

    @ViewChild("file")
    file: any;

    @ViewChild("image")
    imageAux: any;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService, loginService: LoginService) {

      loginService.currentUser2().subscribe(
        user => {
          service.getMember(user.id as number).subscribe(
          mem => this.member = mem as User,
          error => console.error(error),
          );
        },
         _ =>_  );

    }

    save() {
      this.service.updateMember(this.member as User).subscribe(
          _ => window.location.href = "http://localhost:4200/new/memberprofile",
          error => alert('Error updating member: ' + error)
      );
  }
}

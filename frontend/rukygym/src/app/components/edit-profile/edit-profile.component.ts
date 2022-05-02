import { Component } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { LoginService } from "src/app/services/Login.service";

@Component({
  selector: 'editprofile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent {

  constructor(private router: Router, activatedRoute: ActivatedRoute,
    public loginService: LoginService) {



    }



}

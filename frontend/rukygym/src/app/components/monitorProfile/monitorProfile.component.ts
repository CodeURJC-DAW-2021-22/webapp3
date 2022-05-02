import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/User.model';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/User.service';

@Component({
  selector: 'monitorProfile',
  templateUrl: './monitorProfile.component.html',
  styleUrls: ['./monitorProfile.component.css']
})
export class MonitorProfile {

  monitor: User | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService, loginService: LoginService) {

    loginService.currentUser2().subscribe(
      user => {
        service.getMonitor(user.id as number).subscribe(
        monitor => this.monitor = monitor as User,
        error => console.error(error),
      );
      },
       _ =>  _);

  }
}

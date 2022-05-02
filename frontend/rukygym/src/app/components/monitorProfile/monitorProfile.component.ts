import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/User.model';
import { UserService } from 'src/app/services/User.service';

@Component({
  selector: 'monitorProfile',
  templateUrl: './monitorProfile.component.html',
  styleUrls: ['./monitorProfile.component.css']
})
export class MonitorProfile {

  monitor: User | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService) {

      const id = activatedRoute.snapshot.params['id'];
      service.getMonitor(43).subscribe(
          (monitor) => this.monitor = monitor as User,
          (error: any)    => console.error(error)
      );
  }

}

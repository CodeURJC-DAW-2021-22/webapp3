import { Component } from "@angular/core"
import { Router, ActivatedRoute } from "@angular/router";
import { Activity } from "src/app/models/Activity.model";
import { ActivityService } from "src/app/services/Activity.service";
import { UserService } from "src/app/services/User.service";
import { User } from "../../models/User.model";



@Component({
    selector:'monitorschedule',
    templateUrl: './monitorSchedule.component.html',
    styleUrls: ['./monitorSchedule.component.css']
})

export class MonitorSchedule {

  monitor: User | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService) {

      const id = activatedRoute.snapshot.params['id'];
      service.getMonitor(43).subscribe(
          monitor => this.monitor = monitor as User,
          error => console.error(error),
      );
  }

}

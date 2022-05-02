import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { User } from 'src/app/models/User.model';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/User.service';


@Component({
  selector: 'monitorEditProfile',
  templateUrl: './monitorEditProfile.component.html',
  styleUrls: ['./monitorEditProfile.component.css']
})
export class MonitorEditProfile {

  @ViewChild("file")
  file: any;

  monitor: User | undefined;


  @ViewChild("image")
  imageAux: any;


  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService, loginService: LoginService) {

    loginService.currentUser2().subscribe(
      user => {
        service.getMonitor(user.id as number).subscribe(
        monitor => this.monitor = monitor as User,
        error => console.error(error),
        );
      },
      _ => _ );

  }

  save() {
      this.service.updateMonitor(this.monitor as User).subscribe(
          act => this.uploadImg(act),
          error => alert('Error updating activity: ' + error)
      );

      this.router.navigate(["new/profile"]);
  }

  uploadImg(activity: unknown): void {
      const image = this.file.nativeElement.files[0];

      if (image) {
          let formData = new FormData();
          formData.append("imageFile", image);
          this.service.setMonitorImage(activity as User, formData).subscribe(
              _ => _,
              error => alert('Error uploading activity image: ' + error)
          );
      }

      this.router.navigate(["new/profile"]);
  }

}
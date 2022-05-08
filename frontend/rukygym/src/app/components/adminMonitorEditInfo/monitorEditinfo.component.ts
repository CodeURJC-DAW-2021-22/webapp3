import { Component, ViewChild } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { User } from "src/app/models/User.model";
import { UserService } from "src/app/services/User.service";


@Component({
    selector: 'monitorEditInfo',
    templateUrl: './monitorEditInfo.component.html',
    styleUrls: ['monitorEditInfo.component.css']
})
export class MonitorEditInfo {

    monitor: User | undefined;

    @ViewChild("file")
    file: any;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService) {

        const id = activatedRoute.snapshot.params['id'];
        service.getMonitor(id).subscribe(
            (monitor) => this.monitor = monitor as User,
            (error: any)    => console.error(error)
        );

    }

    save(){
        this.service.updateMonitor(this.monitor as User).subscribe(
            monitor => this.updateImg(monitor as User),
            _ => alert("No se ha añadido el nuevo monitor")
        );
    }

    updateImg(monitor: User): void {
        const image = this.file.nativeElement.files[0];

        if (image) {
            let formData = new FormData();
            formData.append("imageFile", image);
            this.service.setMonitorImage(monitor, formData).subscribe(
                _ => {
                    this.router.navigate(["monitors"]);
                },
                error => alert('Error uploading monitor image: ' + error)
            );
        }
    }
}

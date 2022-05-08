import { Component, ViewChild } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { User } from "src/app/models/User.model";
import { UserService } from "src/app/services/User.service";

@Component({
    selector: 'newmonitor',
    templateUrl: './newMonitor.component.html',
    styleUrls: ['./newMonitor.component.css']
})
export class NewMonitor {

    monitor: User = {
        name: "",
        surname: "",
        NIF: "",
        email: "",
        address: "",
        postalCode: "",
        phone: "",
        description: "",
        userType: "monitor",
        medicalInfo: "",
        encodedPassword: ""
      }

    @ViewChild("file")
    file: any;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService) {
    }

    save(){
        this.service.addMonitor(this.monitor as User).subscribe(
            monitor => this.updateImg(monitor as User),
            _ => alert("No se ha añadido el nuevo monitor")
        )
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

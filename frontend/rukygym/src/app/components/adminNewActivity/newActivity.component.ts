import { Component, ViewChild } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { ActivityService } from "src/app/services/activities.service";
import { Activity } from "../../models/Activity.model";

@Component({
    selector: 'newactivity',
    templateUrl: './newActivity.component.html',
    styleUrls: ['./newActivity.component.css']
})
export class NewActivity{
    
    activity : Activity = {
        name: "",
        room: "",
        capacity: 0,
        description: "",
        price: 0,
        monday:	"",
        tuesday: "",
        wednesday: "",
        thursday: "",
        friday:	"",
        monitorName: ""
    };

    @ViewChild("file")
    file: any;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ActivityService) {
    }

    save() {
        if (this.activity.name === "" || this.activity.room === "" || this.activity.capacity === 0 || this.activity.description === "" ||
            this.activity.price === 0 || this.activity.monday === "" || this.activity.tuesday === "" || this.activity.wednesday === "" ||
            this.activity.thursday === "" || this.activity.friday === ""){
                alert("Rellena el formulario")
                return ;
        }

        this.service.addActivity(this.activity as Activity).subscribe(
            activity => this.uploadImg(activity),
            error => alert('Error updating activity: ' + error)
        );
        
    }

    uploadImg(activity: unknown): void {
        const image = this.file.nativeElement.files[0];
    
        if (image) {
            let formData = new FormData();
            formData.append("imageFile", image);
            this.service.setActivityImage(activity as Activity, formData).subscribe(
                _ => {this.router.navigate(["new/activities"]); },
                error => alert('Error uploading activity image: ' + error)
            );
        }
    
        
    }
}



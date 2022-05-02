import { Component, ViewChild } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { Activity } from "src/app/models/Activity.model";
import { ActivityService } from "src/app/services/activities.service";
import { LoginService } from "src/app/services/login.service";

@Component({
    selector: 'EditActivity',
    templateUrl: './editActivity.component.html',
    styleUrls: ['./editActivity.component.css'],
})

export class EditActivity {

    @ViewChild("file")
    file: any;

    activity: Activity | undefined;
    
    @ViewChild("image")
    imageAux: any;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ActivityService,
        public loginService: LoginService) {
        
        const id = activatedRoute.snapshot.params['id'];
        service.getActivity(id).subscribe(
            (activity) => this.activity = activity as Activity,
            (error: any)    => console.error(error)
        );

        
    }


    save() {
        this.service.updateActivity(this.activity as Activity).subscribe(
            act => this.uploadImg(act),
            error => alert('Error updating activity: ' + error)
        );
        
        this.router.navigate(["new/activity/" + this.activity?.id]);
    }

    uploadImg(activity: unknown): void {
        const image = this.file.nativeElement.files[0];
    
        if (image) {
            let formData = new FormData();
            formData.append("imageFile", image);
            this.service.setActivityImage(activity as Activity, formData).subscribe(
                _ => _,
                error => alert('Error uploading activity image: ' + error)
            );
        }
    
        this.router.navigate(["new/activities"]);
    }
}

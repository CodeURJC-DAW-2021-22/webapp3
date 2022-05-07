import { Component } from "@angular/core";


@Component({
    selector:'sidemenu',
    templateUrl: './sidemenu.component.html',
    styleUrls: ['./sidemenu.component.css']
})
export class SidemenuComponent {

    visible: boolean = false;

    classSchedule = {
        "active" : false,
        "notActive" : true,
    }

    classProfile = {
        "active" : false,
        "notActive" : true,
    }

    classExerciseTables = {
        "active" : false,
        "notActive" : true,
    }

    classActivities = {
        "active" : false,
        "notActive" : true,
    }

    constructor(){
        switch (window.location.href) {
            case "http://localhost:4200/new/schedule": this.classSchedule = {
                "active" : true,
                "notActive" : false,
            }
                break;
            case "http://localhost:4200/new/profile": this.classProfile = {
                "active" : true,
                "notActive" : false,
            }
                break;
            case "http://localhost:4200/new/exerciseTables": this.classExerciseTables = {
                "active" : true,
                "notActive" : false,
            }
                break;
            case "http://localhost:4200/new/activities": this.classActivities = {
                "active" : true,
                "notActive" : false,
            }
                break;

        }
    }

    changeStatus() {
        this.visible = !this.visible;
    }

}

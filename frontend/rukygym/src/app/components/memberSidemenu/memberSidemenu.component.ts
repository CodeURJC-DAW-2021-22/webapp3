import { Component } from "@angular/core";

@Component({
    selector:'membersidemenu',
    templateUrl: './memberSidemenu.component.html',
    styleUrls: ['./memberSidemenu.component.css']
})
export class MemberSidemenuComponent {

    visible: boolean = false;

    classStatistics = {
        "active" : false,
        "notActive" : true,
    }

    classActivities = {
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

    constructor(){
        switch (window.location.href) {
            case "http://localhost:4200/new/statistics": this.classStatistics = {
                "active" : true,
                "notActive" : false,
            }
                break;
            case "http://localhost:4200/new/activities": this.classActivities = {
                "active" : true,
                "notActive" : false,
            }
                break;
            case "http://localhost:4200/new/profile": this.classProfile = {
                "active" : true,
                "notActive" : false,
            }
                break;
            case "http://localhost:4200/new/exercises-tables": this.classExerciseTables = {
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

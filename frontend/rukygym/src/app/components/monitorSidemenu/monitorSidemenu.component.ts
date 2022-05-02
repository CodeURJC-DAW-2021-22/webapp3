import { Component } from "@angular/core";

@Component({
    selector: 'monitorsidemenu',
    templateUrl: './monitorSidemenu.component.html',
    styleUrls: ['./monitorSidemenu.component.css']
})

export class MonitorSidemenu {
        
    visible: boolean = false;

    classStatistics = {
        "active" : false,
        "notActive" : true,
    }

    classActivities = {
        "active" : false,
        "notActive" : true,
    }

    classMonitors = {
        "active" : false,
        "notActive" : true,
    }

    classClients = {
        "active" : false,
        "notActive" : true,
    }

    constructor(){
        switch (window.location.href) {
            case "http://localhost:4200/new/schedule": this.classStatistics = {
                "active" : true,
                "notActive" : false,
            }
                break;
            case "http://localhost:4200/new/profile": this.classActivities = {
                "active" : true,
                "notActive" : false,
            }
                break;
            case "http://localhost:4200/new/monitorExerciseTable": this.classMonitors = {
                "active" : true,
                "notActive" : false,
            }
                break;
            case "http://localhost:4200/new/activities": this.classClients = {
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
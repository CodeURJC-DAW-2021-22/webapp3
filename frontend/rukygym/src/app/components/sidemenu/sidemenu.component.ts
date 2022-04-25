import { Component } from "@angular/core";


@Component({
    selector:'sidemenu',
    templateUrl: './sidemenu.component.html',
    styleUrls: ['./sidemenu.component.css']
})
export class SidemenuComponent {
    
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
            case "http://localhost:4200/new/monitors": this.classMonitors = {
                "active" : true,
                "notActive" : false,
            }
                break;
            case "http://localhost:4200/new/clients": this.classClients = {
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
import { Component } from "@angular/core";

@Component({
    selector: 'monitorcontent',
    templateUrl: './monitorContent.component.html',
    styleUrls: ['./monitorContent.component.css']
})

export class MonitorContent {
    classes = {
        "closed" : true,
        "opened" : false,
    }

    changeClasses() {
        this.classes.closed = ! this.classes.closed;
        this.classes.opened = !this.classes.opened;
    }
}
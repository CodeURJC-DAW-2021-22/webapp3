import { Component, ElementRef } from "@angular/core";


@Component({
    selector:'admincontent',
    templateUrl: './content.component.html',
    styleUrls: ['./content.component.css']
})
export class AdminContentComponent{

    classes = {
        "closed" : true,
        "opened" : false,
    }

    changeClasses() {
        this.classes.closed = ! this.classes.closed;
        this.classes.opened = !this.classes.opened;
    }
    
}
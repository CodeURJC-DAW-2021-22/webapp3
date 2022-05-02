import { Component, ElementRef, EventEmitter, Output } from "@angular/core";


@Component({
    selector:'content',
    templateUrl: './content.component.html',
    styleUrls: ['./content.component.css']
})
export class ContentComponent{

    classes = {
        "closed" : true,
        "opened" : false,
    }

    changeClasses() {
        this.classes.closed = ! this.classes.closed;
        this.classes.opened = !this.classes.opened;
    }

    
}
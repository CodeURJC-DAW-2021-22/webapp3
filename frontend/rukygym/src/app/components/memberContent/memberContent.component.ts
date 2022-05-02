import { Component, ElementRef } from "@angular/core";


@Component({
    selector:'membercontent',
    templateUrl: './memberContent.component.html',
    styleUrls: ['./memberContent.component.css']
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

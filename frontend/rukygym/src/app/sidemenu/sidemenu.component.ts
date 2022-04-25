import { Component } from "@angular/core";


@Component({
    selector:'sidemenu',
    templateUrl: './sidemenu.component.html',
    styleUrls: ['./sidemenu.component.css']
})
export class SidemenuComponent {

    visible: boolean = false;

    changeStatus() {
        this.visible = !this.visible;
    }
}

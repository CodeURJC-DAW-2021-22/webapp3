import { Component, EventEmitter, Output } from "@angular/core";


@Component({
    selector:'navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['../../../assets/css/main.css']
})
export class NavbarComponent {

  num = 0;


  responsiveMenu(){
    if(this.num == 0){
      this.num = 1;
    }else{
      this.num = 0;
    }
  }


}

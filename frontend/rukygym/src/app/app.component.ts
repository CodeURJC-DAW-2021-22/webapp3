import { Component } from '@angular/core';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'rukygym';

  logged: boolean = false;

  constructor(service: LoginService) {
    this.logged = service.isLogged() as boolean;
  }

}

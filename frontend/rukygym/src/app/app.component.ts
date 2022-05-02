import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { User } from './models/User.model';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'rukygym';


  notReg = true;
  admin = false;
  mon = false;

  constructor(private http: HttpClient){
    this.http.get('/api/users/monitors/me', { withCredentials: true }).subscribe( 
      (user: any) => {
        switch(user.userType) {
          case 'monitor':
            this.admin = false;
            this.notReg = false;
            this.mon = true;
            break;
          case 'administrator':
            this.admin = true;
            this.notReg = false;
            this.mon = false;
            break;
          case 'member':
            this.admin = false;
            this.notReg = false;
            this.mon = false;
            break;
          default:
            this.admin = false;
            this.notReg = true;
            this.mon = false;
            break;
        }
      },
      _ => {this.admin = false;
        this.notReg = true;
        this.mon = false;}
    );
  }

}

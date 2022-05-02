import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'rukygym';

  visible = true;

  constructor(private http: HttpClient){
    this.http.get('/api/users/monitors/me', { withCredentials: true }).subscribe( 
      _ => this.visible = false,
      _ => this.visible = true,
    );
  }

}

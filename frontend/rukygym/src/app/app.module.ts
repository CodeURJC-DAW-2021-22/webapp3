import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidemenuComponent } from './sidemenu/sidemenu.component';
import { ContentComponent } from './content/content.component';
import { ProfileComponent } from './profile/profile.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { ActivitiesComponent } from './activities/activities.component'


@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    NavbarComponent,
    SidemenuComponent,
    ContentComponent,
    ProfileComponent,
    EditProfileComponent,
    ActivitiesComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule, NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

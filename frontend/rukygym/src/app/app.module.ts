import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SidemenuComponent } from './components/sidemenu/sidemenu.component';
import { ContentComponent } from './components/content/content.component';
import { ProfileComponent } from './components/profile/profile.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { ActivitiesComponent } from './components/activities/activities.component';
import { ExercisetablesComponent } from './components/exercisetables/exercisetables.component';
import { StatisticsComponent } from './components/statistics/statistics.component'
import { routing } from './app.routing';
import { ActivityInfoComponent } from './components/activity-info/activity-info.component';

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
    ExercisetablesComponent,
    StatisticsComponent,
    ActivityInfoComponent,
  ],

  imports: [
    BrowserModule,
    FormsModule,
    NgbModule,
    routing,
    CommonModule,
    HttpClientModule,
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

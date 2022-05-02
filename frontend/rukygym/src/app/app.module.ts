import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { FooterComponent } from './components/memberFooter/memberFooter.component';
import { NavbarComponent } from './components/memberNavbar/memberNavbar.component';
import { SidemenuComponent } from './components/memberSidemenu/memberSidemenu.component';
import { ContentComponent } from './components/memberContent/memberContent.component';
import { ProfileComponent } from './components/memberProfile/memberProfile.component';
import { EditProfileComponent } from './components/memberEdit-profile/memberEdit-profile.component';
import { ActivitiesComponent } from './components/memberActivities/memberActivities.component';
import { ExercisetablesComponent } from './components/memberExercisetables/memberExercisetables.component';
import { MemberStatistics } from './components/memberStatistics/memberStatistics.component'
import { routing } from './app.routing';
import { ActivityInfoComponent } from './components/memberActivity-info/memberActivity-info.component';

import * as echarts from 'echarts';
import { NgxEchartsModule } from 'ngx-echarts';

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
    MemberStatistics,
    ActivityInfoComponent,

  ],

  imports: [
    BrowserModule,
    FormsModule,
    NgbModule,
    routing,
    CommonModule,
    HttpClientModule,
    NgxEchartsModule.forRoot({
      echarts
    }),
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

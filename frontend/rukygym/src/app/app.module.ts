
import { CommonModule } from '@angular/common';

import { ContactUsComponent } from './components/contactUs/contactUs.component';
import { routing } from './app.routing';


import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { HttpClientModule } from '@angular/common/http';

import { MonitorFooter } from './components/monitorFooter/monitorFooter.component';
import { AppComponent } from './app.component';
import { MemberFooterComponent } from './components/memberFooter/memberFooter.component';
import { MemberNavbarComponent } from './components/memberNavbar/memberNavbar.component';
import { MemberSidemenuComponent } from './components/memberSidemenu/memberSidemenu.component';
import { MemberContentComponent } from './components/memberContent/memberContent.component';
import { MemberProfileComponent } from './components/memberProfile/memberProfile.component';
import { MemberEditProfileComponent } from './components/memberEdit-profile/memberEdit-profile.component';
import { MemberActivitiesComponent } from './components/memberActivities/memberActivities.component';
import { MemberExercisetablesComponent } from './components/memberExercisetables/memberExercisetables.component';
import { MemberStatistics } from './components/memberStatistics/memberStatistics.component'
import { MemberActivityInfoComponent } from './components/memberActivity-info/memberActivity-info.component';

import { MonitorNavbar } from './components/monitorNavbar/monitorNavbar.component';
import { MonitorSidemenu } from './components/monitorSidemenu/monitorSidemenu.component';
import { MonitorContent } from './components/monitorContent/monitorContent.component';
import { MonitorActivities } from './components/monitorActivities/monitorActivities.component';
import { MonitorActivityInfo } from './components/monitorActivityInfo/monitorActivityInfo.component';
import { MonitorSchedule } from './components/monitorSchedule/monitorSchedule.component';
import { MonitorProfile } from './components/monitorProfile/monitorProfile.component';
import { MonitorEditProfile } from './components/monitorEditProfile/monitorEditProfile.component';
import { MonitorExerciseTable } from './components/monitorExerciseTable/monitorExerciseTable.component';
import { MonitorAddExerciseTable } from './components/monitorAddExerciseTable/monitorAddExerciseTable.component';
import { MonitorEditExerciseTable } from './components/monitorEditExerciseTable/monitorEditExerciseTable.component';
import { MonitorExerciseTableInfo } from './components/monitorExerciseTableInfo/monitorExerciseTableInfo.component';

import { SidemenuComponent } from './components/adminSidemenu/sidemenu.component';
import { AdminNavbarComponent } from './components/adminNavbar/navbar.component';
import { AdminContentComponent } from './components/adminContent/content.component';
import { AdminFooterComponent } from './components/adminFooter/footer.component';
import { AdminStatistics } from './components/adminStatistics/adminStatistics.component';
import { AdminActivities } from './components/adminActivities/adminActivities.component';
import { ActivityInfo } from './components/adminActivityInfo/activityInfo.component'
import { ClientTable } from './components/adminClients/client.component';
import { MonitorInfo } from './components/adminMonitorInfo/monitorInfo.component';
import { AdminMonitors } from './components/adminMonitors/adminmonitors.component';
import { MonitorEditInfo } from './components/adminMonitorEditInfo/monitorEditinfo.component';
import { EditActivity } from './components/adminEditActivity/editActivity.component';
import * as echarts from 'echarts';
import { NgxEchartsModule } from 'ngx-echarts';
import { NewActivity } from './components/adminNewActivity/newActivity.component';
import { NewMonitor } from './components/adminNewMonitor/newMonitor.component';

import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { MainPageComponent } from './components/mainPage/mainPage.component';
import { PricesComponent } from './components/prices/prices.component';
import { ContentComponent } from './components/content/content.component';
import { ScheduleComponent } from './components/schedule/schedule.component';
import { ActivitiesComponent } from './components/activities/activities.component';
import { LoginComponent } from './components/log-in/log-in.component';
import { SigninComponent } from './components/sign-in/sign-in.component';



@NgModule({
  declarations: [
    AppComponent,
    MemberFooterComponent,
    NavbarComponent,
    MemberSidemenuComponent,
    MemberContentComponent,
    MemberProfileComponent,
    MemberEditProfileComponent,
    MemberActivitiesComponent,
    MemberExercisetablesComponent,
    MemberStatistics,
    MemberActivityInfoComponent,
    MemberNavbarComponent,
    SidemenuComponent,
    ContentComponent,
    FooterComponent,
    MonitorActivities,
    ActivityInfo,
    MonitorSchedule,
    MonitorProfile,
    MonitorEditProfile,
    MonitorExerciseTable,
    MonitorExerciseTableInfo,
    MonitorEditExerciseTable,
    MonitorAddExerciseTable,
    AppComponent,
    NavbarComponent,
    SidemenuComponent,
    ContentComponent,
    FooterComponent,
    AdminStatistics,
    AdminActivities,
    ActivityInfo,
    ClientTable,
    MonitorInfo,
    AdminMonitors,
    MonitorEditInfo,
    EditActivity,
    NewActivity,
    NewMonitor,
    MainPageComponent,
    PricesComponent,
    ScheduleComponent,
    ContactUsComponent,
    ActivitiesComponent,
    LoginComponent,
    SigninComponent,
    AdminFooterComponent,
    AdminNavbarComponent,
    AdminContentComponent,
    MonitorNavbar,
    MonitorFooter,
    MonitorSidemenu,
    MonitorContent,
    MonitorActivityInfo,
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

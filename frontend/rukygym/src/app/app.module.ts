import { ContactUsComponent } from './components/contactUs/contactUs.component';
import { routing } from './app.routing';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { SidemenuComponent } from './components/adminSidemenu/sidemenu.component';
import { NavbarComponent } from './components/adminNavbar/navbar.component';
import { ContentComponent } from './components/adminContent/content.component';
import { FooterComponent } from './components/adminFooter/footer.component';
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
    SigninComponent
  ],
  imports: [
    BrowserModule, 
    FormsModule, 
    NgbModule,
    routing,
    HttpClientModule,
    NgxEchartsModule.forRoot({
      echarts
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

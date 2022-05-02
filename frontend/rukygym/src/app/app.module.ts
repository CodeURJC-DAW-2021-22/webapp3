import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { routing } from './app.routing';
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
    NewMonitor
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

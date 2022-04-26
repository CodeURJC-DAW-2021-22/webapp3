import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { routing } from './app.routing';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { SidemenuComponent } from './components/sidemenu/sidemenu.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ContentComponent } from './components/content/content.component';
import { FooterComponent } from './components/footer/footer.component';
import { AdminStatistics } from './components/adminStatistics/adminStatistics.component';
import { AdminActivities } from './components/adminActivities/adminActivities.component';
import { ActivityInfo } from './components/activityInfo/activityInfo.component'
import { ClientTable } from './components/clients/client.component';
import { MonitorInfo } from './components/monitorInfo/monitorInfo.component';
import { AdminMonitors } from './components/adminMonitors/adminmonitors.component';

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
  ],
  imports: [
    BrowserModule, 
    FormsModule, 
    NgbModule,
    routing,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

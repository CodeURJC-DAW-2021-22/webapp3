import { Routes, RouterModule } from '@angular/router';

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
import { NewActivity } from './components/adminNewActivity/newActivity.component';
import { NewMonitor } from './components/adminNewMonitor/newMonitor.component';


const appRoutes = [
    {path: 'new/activities', component: AdminActivities},
    {path: 'new/activity/:id', component: ActivityInfo},
    {path: 'new/activity/edit/:id', component: EditActivity},
    {path: 'new/monitors', component: AdminMonitors},
    {path: 'new/monitor/:id', component: MonitorInfo},
    {path: 'new/create/monitor', component: NewMonitor},
    {path: 'new/monitor/edit/:id', component : MonitorEditInfo},
    {path: 'new/clients', component: ClientTable},
    {path: 'new/statistics', component: AdminStatistics},
    {path: 'new/create/activity', component: NewActivity},

    
]

export const routing = RouterModule.forRoot(appRoutes);
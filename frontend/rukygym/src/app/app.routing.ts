import { Routes, RouterModule } from '@angular/router';

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
import { MonitorEditInfo } from './components/monitorEditInfo/monitorEditinfo.component';
import { EditActivity } from './components/editActivity/editActivity.component';
import { NewActivity } from './components/newActivity/newActivity.component';


const appRoutes = [
    {path: 'new/activities', component: AdminActivities},
    {path: 'new/activity/:id', component: ActivityInfo},
    {path: 'new/activity/edit/:id', component: EditActivity},
    {path: 'new/monitors', component: AdminMonitors},
    {path: 'new/monitor/:id', component: MonitorInfo},
    {path: 'new/monitor/edit/:id', component : MonitorEditInfo},
    {path: 'new/clients', component: ClientTable},
    {path: 'new/statistics', component: AdminStatistics},
    {path: 'new/create/activity', component: NewActivity},

    
]

export const routing = RouterModule.forRoot(appRoutes);
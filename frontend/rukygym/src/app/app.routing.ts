import { Routes, RouterModule } from '@angular/router';

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


import { SigninComponent } from './components/sign-in/sign-in.component';
import { LoginComponent } from './components/log-in/log-in.component';
import { PricesComponent } from './components/prices/prices.component';



import { MainPageComponent } from './components/mainPage/mainPage.component';
import { ContactUsComponent } from './components/contactUs/contactUs.component';
import { ActivitiesComponent } from './components/activities/activities.component';
import { ScheduleComponent } from './components/schedule/schedule.component';


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
    {path: 'new/mainPage', component: MainPageComponent},
    {path: 'new/prices', component: PricesComponent},
    {path: 'new/contactUs', component: ContactUsComponent},
    {path: 'new/main-activities', component: ActivitiesComponent},
    {path: 'new/main-activities/schedule/:id', component: ScheduleComponent},
    {path: 'new/log-in', component: LoginComponent},
    {path: 'new/sign-in', component: SigninComponent}
    
]

export const routing = RouterModule.forRoot(appRoutes);
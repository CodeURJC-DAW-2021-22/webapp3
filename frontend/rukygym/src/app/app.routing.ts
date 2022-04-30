import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { SidemenuComponent } from './components/sidemenu/sidemenu.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ContentComponent } from './components/content/content.component';
import { FooterComponent } from './components/footer/footer.component';
import { ActivityInfo } from './components/activityInfo/activityInfo.component';
import { MonitorActivities } from './components/monitorActivities/monitorActivities.component';
import { MonitorSchedule } from './components/monitorSchedule/monitorSchedule.component';



const appRoutes = [
    {path: 'new/activities', component: MonitorActivities},
    {path: 'new/activity/:id', component: ActivityInfo},
    {path: 'new/schedule', component: MonitorSchedule},
]

export const routing = RouterModule.forRoot(appRoutes);

import { MonitorAddExerciseTable } from './components/monitorAddExerciseTable/monitorAddExerciseTable.component';
import { MonitorExerciseTableInfo } from './components/monitorExerciseTableInfo/monitorExerciseTableInfo.component';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { SidemenuComponent } from './components/sidemenu/sidemenu.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ContentComponent } from './components/content/content.component';
import { FooterComponent } from './components/footer/footer.component';
import { ActivityInfo } from './components/activityInfo/activityInfo.component';
import { MonitorActivities } from './components/monitorActivities/monitorActivities.component';
import { MonitorSchedule } from './components/monitorSchedule/monitorSchedule.component';
import { MonitorProfile } from './components/monitorProfile/monitorProfile.component';
import { MonitorEditProfile } from './components/monitorEditProfile/monitorEditProfile.component';
import { MonitorExerciseTable } from './components/monitorExerciseTable/monitorExerciseTable.component';
import { MonitorEditExerciseTable } from './components/monitorEditExerciseTable/monitorEditExerciseTable.component';



const appRoutes = [
    {path: 'new/activities', component: MonitorActivities},
    {path: 'new/activity/:id', component: ActivityInfo},
    {path: 'new/schedule', component: MonitorSchedule},
    {path: 'new/profile', component: MonitorProfile},
    {path: 'new/monitorEditProfile', component: MonitorEditProfile},
    {path: 'new/monitorExerciseTable', component : MonitorExerciseTable},
    {path: 'new/monitorExerciseTableInfo/:id', component: MonitorExerciseTableInfo},
    {path: 'new/monitorEditExerciseTable/:id', component: MonitorEditExerciseTable},
    {path: 'new/monitorAddExerciseTable', component: MonitorAddExerciseTable},

]

export const routing = RouterModule.forRoot(appRoutes);

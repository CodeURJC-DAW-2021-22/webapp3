
import { MonitorAddExerciseTable } from './components/monitorAddExerciseTable/monitorAddExerciseTable.component';
import { MonitorExerciseTableInfo } from './components/monitorExerciseTableInfo/monitorExerciseTableInfo.component';
import { MonitorActivityInfo } from './components/monitorActivityInfo/monitorActivityInfo.component';
import { MonitorActivities } from './components/monitorActivities/monitorActivities.component';
import { MonitorSchedule } from './components/monitorSchedule/monitorSchedule.component';
import { MonitorProfile } from './components/monitorProfile/monitorProfile.component';
import { MonitorEditProfile } from './components/monitorEditProfile/monitorEditProfile.component';
import { MonitorExerciseTable } from './components/monitorExerciseTable/monitorExerciseTable.component';
import { MonitorEditExerciseTable } from './components/monitorEditExerciseTable/monitorEditExerciseTable.component';

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




import { MemberActivitiesComponent } from './components/memberActivities/memberActivities.component';
import { MemberExercisetablesComponent } from './components/memberExercisetables/memberExercisetables.component'
import { MemberEditProfileComponent } from './components/memberEdit-profile/memberEdit-profile.component';
import { MemberProfileComponent } from './components/memberProfile/memberProfile.component';
import { MemberStatistics } from './components/memberStatistics/memberStatistics.component';
import { MemberActivityInfoComponent } from './components/memberActivity-info/memberActivity-info.component';


const appRoutes = [
    {path: 'activities', component: AdminActivities},
    {path: 'activity/:id', component: ActivityInfo},
    {path: 'activity/edit/:id', component: EditActivity},
    {path: 'monitors', component: AdminMonitors},
    {path: 'monitor/:id', component: MonitorInfo},
    {path: 'create/monitor', component: NewMonitor},
    {path: 'monitor/edit/:id', component : MonitorEditInfo},
    {path: 'clients', component: ClientTable},
    {path: 'statistics', component: AdminStatistics},
    {path: 'create/activity', component: NewActivity},
    {path: 'mainPage', component: MainPageComponent},
    {path: 'prices', component: PricesComponent},
    {path: 'contactUs', component: ContactUsComponent},
    {path: 'main-activities', component: ActivitiesComponent},
    {path: 'main-activities/schedule/:id', component: ScheduleComponent},
    {path: 'log-in', component: LoginComponent},
    {path: 'sign-in', component: SigninComponent},
    {path: 'monitorActivities', component: MonitorActivities},
    {path: 'schedule', component: MonitorSchedule},
    {path: 'profile', component: MonitorProfile},
    {path: 'monitorEditProfile', component: MonitorEditProfile},
    {path: 'monitorExerciseTable', component : MonitorExerciseTable},
    {path: 'monitorExerciseTableInfo/:id', component: MonitorExerciseTableInfo},
    {path: 'monitorEditExerciseTable/:id', component: MonitorEditExerciseTable},
    {path: 'monitorAddExerciseTable', component: MonitorAddExerciseTable},
    {path: 'monitorActivityInfo/:id', component: MonitorActivityInfo},
    {path: 'memberactivities', component: MemberActivitiesComponent},
    {path: 'memberexercise-tables', component: MemberExercisetablesComponent},
    {path: 'profile/edit/:id', component: MemberEditProfileComponent},
    {path: 'memberprofile', component: MemberProfileComponent},
    {path: 'memberstatistics', component: MemberStatistics},
    {path: 'memberactivity/:id', component: MemberActivityInfoComponent},
]

export const routing = RouterModule.forRoot(appRoutes);

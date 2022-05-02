import { Routes, RouterModule } from '@angular/router';

import { ActivitiesComponent } from './components/memberActivities/memberActivities.component';
import { ExercisetablesComponent } from './components/memberExercisetables/memberExercisetables.component'
import { EditProfileComponent } from './components/memberEdit-profile/memberEdit-profile.component';
import { ProfileComponent } from './components/memberProfile/memberProfile.component';
import { MemberStatistics } from './components/memberStatistics/memberStatistics.component';
import { ActivityInfoComponent } from './components/memberActivity-info/memberActivity-info.component';


const appRoutes = [
    {path: 'new/activities', component: ActivitiesComponent},
    {path: 'new/exercise-tables', component: ExercisetablesComponent},
    {path: 'new/profile/edit/:id', component: EditProfileComponent},
    {path: 'new/profile', component: ProfileComponent},
    {path: 'new/statistics', component: MemberStatistics},
    {path: 'new/activity/edit/:id', component: ActivityInfoComponent},


]

export const routing = RouterModule.forRoot(appRoutes);

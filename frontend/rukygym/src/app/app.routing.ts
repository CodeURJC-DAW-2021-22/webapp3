import { Routes, RouterModule } from '@angular/router';

import { ActivitiesComponent } from './components/activities/activities.component';
import { ExercisetablesComponent } from './components/exercisetables/exercisetables.component'
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { ProfileComponent } from './components/profile/profile.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { ActivityInfoComponent } from './components/activity-info/activity-info.component';

const appRoutes = [
    {path: 'new/activities', component: ActivitiesComponent},
    {path: 'new/exercise-tables', component: ExercisetablesComponent},
    {path: 'new/profile/edit/:id', component: EditProfileComponent},
    {path: 'new/profile', component: ProfileComponent},
    {path: 'new/statistics', component: StatisticsComponent},
    {path: 'new/activity/edit/:id', component: ActivityInfoComponent},
]

export const routing = RouterModule.forRoot(appRoutes);

import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { SidemenuComponent } from './components/sidemenu/sidemenu.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ContentComponent } from './components/content/content.component';
import { FooterComponent } from './components/footer/footer.component';
import { ActivitiesComponent } from './components/activities/activities.component';
import { ExercisetablesComponent } from './components/exercisetables/exercisetables.component'
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { ProfileComponent } from './components/profile/profile.component';
import { StatisticsComponent } from './components/statistics/statistics.component';

const appRoutes = [
    {path: 'new/activities', component: ActivitiesComponent},
    {path: 'new/exercise-tables', component: ExercisetablesComponent},
    {path: 'new/profile/edit/:id', component: EditProfileComponent},
    {path: 'new/profile', component: ProfileComponent},
    {path: 'new/statistics', component: StatisticsComponent},
]

export const routing = RouterModule.forRoot(appRoutes);

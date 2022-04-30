import { PricesComponent } from './components/prices/prices.component';
import { Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MainPageComponent } from './components/mainPage/mainPage.component';
import { ContentComponent } from './components/content/content.component';
import { FooterComponent } from './components/footer/footer.component';
import { ContactUsComponent } from './components/contactUs/contactUs.component';
import { ActivitiesComponent } from './components/activities/activities.component';
import { ScheduleComponent } from './components/schedule/schedule.component';

const appRoutes = [
    /*{path: 'new/activities', component: AdminActivities},
    {path: 'new/activity/:id', component: ActivityInfo},
    {path: 'new/activity/edit/:id', component: ActivityInfo},
    {path: 'new/monitors', component: AdminMonitors},
    {path: 'new/monitor/:id', component: MonitorInfo},
    {path: 'new/clients', component: ClientTable},
    {path: 'new/statistics', component: AdminStatistics},
    {path: 'new/activities', component: AdminActivities},
    {path: 'new/activities', component: AdminActivities},
    {path: 'new/activities', component: AdminActivities},*/
    {path: 'new/mainPage', component: MainPageComponent},
    {path: 'new/prices', component: PricesComponent},
    {path: 'new/contactUs', component: ContactUsComponent},
    {path: 'new/main-activities', component: ActivitiesComponent},
    {path: 'new/main-activities/schedule/:id', component: ScheduleComponent}
]

export const routing = RouterModule.forRoot(appRoutes);

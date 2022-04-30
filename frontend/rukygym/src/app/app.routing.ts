import { SigninComponent } from './components/sign-in/sign-in.component';
import { LoginComponent } from './components/log-in/log-in.component';
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
    {path: 'new/mainPage', component: MainPageComponent},
    {path: 'new/prices', component: PricesComponent},
    {path: 'new/contactUs', component: ContactUsComponent},
    {path: 'new/main-activities', component: ActivitiesComponent},
    {path: 'new/main-activities/schedule/:id', component: ScheduleComponent},
    {path: 'new/log-in', component: LoginComponent},
    {path: 'new/sign-in', component: SigninComponent}
]

export const routing = RouterModule.forRoot(appRoutes);

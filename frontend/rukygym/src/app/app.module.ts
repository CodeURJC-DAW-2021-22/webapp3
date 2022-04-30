import { ContactUsComponent } from './components/contactUs/contactUs.component';
import { routing } from './app.routing';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';

import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { MainPageComponent } from './components/mainPage/mainPage.component';
import { PricesComponent } from './components/prices/prices.component';
import { ContentComponent } from './components/content/content.component';
import { ScheduleComponent } from './components/schedule/schedule.component';
import { ActivitiesComponent } from './components/activities/activities.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    MainPageComponent,
    PricesComponent,
    ContentComponent,
    ScheduleComponent,
    ContactUsComponent,
    ActivitiesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NgbModule,
    HttpClientModule,
    routing,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { SidemenuComponent } from './components/sidemenu/sidemenu.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ContentComponent } from './components/content/content.component';
import { FooterComponent } from './components/footer/footer.component';
import { AdminStatistics } from './components/adminStatistics/adminStatistics.component';
import { AdminActivities } from './components/adminActivities/adminActivities.component';

@NgModule({
  declarations: [
    AppComponent, NavbarComponent, SidemenuComponent, ContentComponent, FooterComponent, AdminStatistics, AdminActivities,
  ],
  imports: [
    BrowserModule, 
    FormsModule, NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

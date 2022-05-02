
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { routing } from './app.routing';
import { HttpClientModule } from '@angular/common/http';

import { FooterComponent } from './components/footer/footer.component';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SidemenuComponent } from './components/sidemenu/sidemenu.component';
import { ContentComponent } from './components/content/content.component';
import { MonitorActivities } from './components/monitorActivities/monitorActivities.component';
import { ActivityInfo } from './components/activityInfo/activityInfo.component';
import { MonitorSchedule } from './components/monitorSchedule/monitorSchedule.component';
import { MonitorProfile } from './components/monitorProfile/monitorProfile.component';
import { MonitorEditProfile } from './components/monitorEditProfile/monitorEditProfile.component';
import { MonitorExerciseTable } from './components/monitorExerciseTable/monitorExerciseTable.component';
import { MonitorAddExerciseTable } from './components/monitorAddExerciseTable/monitorAddExerciseTable.component';
import { MonitorEditExerciseTable } from './components/monitorEditExerciseTable/monitorEditExerciseTable.component';
import { MonitorExerciseTableInfo } from './components/monitorExerciseTableInfo/monitorExerciseTableInfo.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SidemenuComponent,
    ContentComponent,
    FooterComponent,
    MonitorActivities,
    ActivityInfo,
    MonitorSchedule,
    MonitorProfile,
    MonitorEditProfile,
    MonitorExerciseTable,
    MonitorExerciseTableInfo,
    MonitorEditExerciseTable,
    MonitorAddExerciseTable,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NgbModule,
    routing,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

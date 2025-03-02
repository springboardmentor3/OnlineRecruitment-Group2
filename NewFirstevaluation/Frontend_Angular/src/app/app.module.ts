import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { ForgotpasswordComponent } from './components/forgotpassword/forgotpassword.component';
import { HomeComponent } from './components/home/home.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { JobSearchComponent } from './components/job-search/job-search.component';
import { LoginSuccessComponent } from './components/login-success/login-success.component';
import { EmailVerificationComponent } from './components/email-verification/email-verification.component';
import { EmailService } from './email.service';
import { HttpClientModule } from '@angular/common/http';
import { AppointmentComponent } from './components/appointment/appointment.component';
import { EmpLoginComponent } from './components/emp-login/emp-login.component';
import { EmpRegisterComponent } from './components/emp-register/emp-register.component';
import { StudLoginComponent } from './components/stud-login/stud-login.component';
import { StudRegisterComponent } from './components/stud-register/stud-register.component';
import { JobSeekerComponent } from './components/job-seeker/job-seeker.component';
import { JobRegisterComponent } from './components/job-register/job-register.component';
import { JobdescriptionComponent } from './jobdescription/jobdescription.component';
import { JoblistComponent } from './joblist/joblist.component';
import { AppliedJobListComponent } from './components/applied-job-list/applied-job-list.component';
import { AdminDashComponent } from './components/admin-dash/admin-dash.component';
import { GradDashComponent } from './components/grad-dash/grad-dash.component';
import { EmpDashComponent } from './components/emp-dash/emp-dash.component';
import { JobDashComponent } from './components/job-dash/job-dash.component';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { EmpHomePageComponent } from './components/emp-home-page/emp-home-page.component';
import { ScheduledComponent } from './components/scheduled/scheduled.component';
import { NotScheduledComponent } from './components/not-scheduled/not-scheduled.component';

import { SafeHtmlPipe } from './safe-html.pipe';
import { ServicesComponent } from './components/services/services.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { BlogsComponent } from './components/blogs/blogs.component';
import { GradmessageComponent } from './components/gradmessage/gradmessage.component';
import { MessagedashComponent } from './components/messagedash/messagedash.component';
import { GradinboxComponent } from './components/gradinbox/gradinbox.component';
import { AdmininboxComponent } from './components/admininbox/admininbox.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    ForgotpasswordComponent,
    HomeComponent,
    JobSearchComponent,
    LoginSuccessComponent,
    EmailVerificationComponent,
    AppointmentComponent,
    EmpLoginComponent,
    EmpRegisterComponent,
    StudLoginComponent,
    StudRegisterComponent,
    JobSeekerComponent,
    JobRegisterComponent,
    JobdescriptionComponent,
    JoblistComponent,
    AppliedJobListComponent,
    AdminDashComponent,
    GradDashComponent,
    EmpDashComponent,
    JobDashComponent,
    AdminLoginComponent,
    EmpHomePageComponent,
    ScheduledComponent,
    NotScheduledComponent,
    SafeHtmlPipe,
    ServicesComponent,
    AboutUsComponent,
    BlogsComponent,
    GradmessageComponent,
    MessagedashComponent,
    GradinboxComponent,
    AdmininboxComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    HttpClientModule
  ],
  providers: [EmailService],
  bootstrap: [AppComponent]
})
export class AppModule { }

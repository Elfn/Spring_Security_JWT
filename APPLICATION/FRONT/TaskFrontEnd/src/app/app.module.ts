import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';



import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { TasksComponent } from './components/tasks/tasks.component';
import { NewTaskComponent } from './components/new-task/new-task.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { AuthenticationService } from './services/authentication.service';

const appRoutes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "tasks", component: TasksComponent },
  { path: "new-task", component: NewTaskComponent },
  { path: "register", component: RegistrationComponent },
  { path: '', redirectTo: "login", pathMatch: "full" }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TasksComponent,
    NewTaskComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule, RouterModule.forRoot(appRoutes), FormsModule, HttpClientModule
  ],
  providers: [AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }

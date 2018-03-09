import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  tasks;
  constructor(private auth: AuthenticationService,private router: Router) { }

  ngOnInit() {
    this.auth.getTasks().subscribe(
    data=>{
        this.tasks = data;
    },
    err=>{
      //this.auth.logout();
      this.router.navigateByUrl("/login"); 
    });
  }

  onNewtask(){
    this.router.navigateByUrl("/new-task");
  }

  isAdmin()
  {
   this.auth.isAdmin();
  }

}

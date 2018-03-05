import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  //islogged = true;
  constructor(private auth: AuthenticationService, private router: Router){}

  // onLogout(){
  //   this.auth.logout();
  //   this.islogged = false;
  //   this.router.navigateByUrl("/login");
  // }

  onLogout()
  {
    if(this.auth.checkLogin())
    {
      this.auth.logout();
     // this.islogged = false;
      this.router.navigateByUrl("/login");
    }
  }

}

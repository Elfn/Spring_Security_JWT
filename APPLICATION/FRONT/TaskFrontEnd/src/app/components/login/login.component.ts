import { Router } from '@angular/router';
import { Component, OnInit,Input } from '@angular/core';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  mode: number = 0;
  constructor(private authService: AuthenticationService,private router: Router) { }

  ngOnInit() {
  }

  onLogin(user) {
    this.authService.login(user)
      .subscribe(
      resp => {
        //server response which hold authorization token
        let jwt = resp.headers.get('authorization');
        //console.log(resp.headers.get('Authorization'));
        this.authService.saveToken(jwt);
        this.router.navigateByUrl('/tasks');
       },
      err => { 
        this.mode = 1;
      }
      );
  }

}

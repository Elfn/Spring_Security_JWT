import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import {JwtHelper} from 'angular2-jwt';
@Injectable()
export class AuthenticationService {

  private host = "http://localhost:8080";
  private jwtToken = null;
  private roles: Array<any>;
  constructor(private http: HttpClient) { }

  login(user)
  {
    //Observe attribute is  to say we do not want the body in json format
    return this.http.post(this.host+"/login",user,{observe: 'response'});
  }
loadToken()
  {
    this.jwtToken = localStorage.getItem('token');
  }
  saveToken(jwt: string)
  {
    this.jwtToken = jwt;
    localStorage.setItem('token',jwt);
    let jwtHelper = new JwtHelper();
    this.roles = jwtHelper.decodeToken(this.jwtToken).roles;
  }

  

  getTasks(){
    if(this.jwtToken == null) this.loadToken()

    return this.http.get(this.host+"/tasks",{headers: new HttpHeaders({'authorization': this.jwtToken})});
  }

  logout()
  {
    this.jwtToken = null;
    localStorage.removeItem('token');
  }

  checkLogin() {
    if (localStorage.getItem("token")!=null && localStorage.getItem("token")!='') {
      console.log(localStorage.getItem("token"));
      return true;
    } else {
      return false;
    }
  }


  isAdmin()
  {
      for(let r of this.roles)
      {
        if(r.authority == 'ADMIN') return true;
      }
      return false;
  }

}

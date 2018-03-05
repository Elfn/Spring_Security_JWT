import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';

@Injectable()
export class AuthenticationService {

  private host = "http://localhost:8080";
  private jwtToken = null;
  constructor(private http: HttpClient) { }

  login(user)
  {
    //Observe attribute is  to say we do not want the body in json format
    return this.http.post(this.host+"/login",user,{observe: 'response'});
  }

  saveToken(jwt: string)
  {
    localStorage.setItem('token',jwt);
  }

  loadToken()
  {
    this.jwtToken = localStorage.getItem('token');
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

}

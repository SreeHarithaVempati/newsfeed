import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { login } from 'src/shared/model/login';
import { JwtResponse } from 'src/shared/model/JwtResponse';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  http:HttpClient;
  statusRes:String;
  userMailId:string;
  constructor(h:HttpClient) {
     this.http=h;

   }
   public  ValidateUser(log:login){
      console.log("login service");
      console.log(log.userMailId);
      console.log(log.userPassword);
      
      return this.http.post<JwtResponse>("http://localhost:6854/login",log);

  }
}

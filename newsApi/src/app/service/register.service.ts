import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Users } from 'src/shared/model/Users';
import { login } from 'src/shared/model/login';
import { Observable } from 'rxjs';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  http: HttpClient;
  userMailId: string;
  userId: BigInteger;

  statusRes: String;
  constructor(h: HttpClient) {
    this.http = h;
  }

  public createUser(register: Users) {
    console.log("Hii")
    console.log("Register service");
    console.log(register.userName);


    return this.http.post("http://localhost:6854/SignUp", register, httpOptions);

  }
  getUser(): Observable<login[]> {
    return this.http.get<[]>("signup", httpOptions);
  }

}

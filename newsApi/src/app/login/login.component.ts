import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import { login } from 'src/shared/model/login';
import { JwtResponse } from 'src/shared/model/JwtResponse';
import { TokenStorageService } from '../service/token-storage.service';
import { RegisterService } from '../service/register.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 submitted=false;
  login1:login=new login();
  loginForm: FormGroup;
 loginService:LoginService;
  formbuilder:FormBuilder
  token:string;
  user:JwtResponse;
  error:any;
  roles: string[] = [];
  userId:BigInteger;
  

  constructor( private fb:FormBuilder,
    logService:LoginService,
    private router:Router,private tokenStorage:TokenStorageService ,
    private registerService:RegisterService) {
      this.loginService=logService;
      this.loginForm=this.fb.group({
        mailId:['',Validators.required],
        password:['',[Validators.required, Validators.minLength(6)]]
    });
   
  
        }

  ngOnInit() {
    
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
    }

  }
  
 
  get f(){
    return this.loginForm.controls;
  }

  backToRegisterPage()
  {
    this.router.navigate(['']);
  }

  validateLogin()
  {
    this.submitted=true;
    if(this.loginForm.invalid)
    {
      return;
    }
    
    else
    {
    console.log("login component");
    console.log(this.loginForm.value);

    this.login1.userMailId=this.loginForm.get('mailId').value;
    this.login1.userPassword=this.loginForm.get('password').value;
    console.log( this.login1.userPassword);
    
    this.loginService.ValidateUser(this.login1).subscribe(      
     data=>{
      if (data.accessToken === null) {
        alert("U are not allowed to access this site");
        this.router.navigate(['login']);

      }
      else{
       this.tokenStorage.saveToken(data.accessToken);
       this.user=data;
    
       console.log(this.user);
       console.log(data.userId);
       this.registerService.userMailId=this.user.name;
       console.log( this.registerService.userMailId);
       this.registerService.userId= this.user.userId;
       
       this.userId=this.user.userId;
      //  localStorage.setItem("accessToken",data.accessToken);
      //  localStorage.setItem("userId",""+this.userId);
       console.log(this.userId);
       console.log(this.registerService.userId);
       
       this.loginService.userMailId=this.login1.userMailId;
       this.tokenStorage.saveId(this.user.userId);

       this.tokenStorage.saveUsername(this.login1.userMailId);
       this.tokenStorage.saveAuthorities(data.role);
       this.roles = this.tokenStorage.getAuthorities();

        if(this.user.role[0].authority==="ROLE_NEWSANALYST")
        {
          alert("successfully logged in as News Analyst");
          this.router.navigate(['home']);
        }
        else{
          alert("successfully logged in as Admin");
          this.router.navigate(['admin']);
        }
      }
     }


    // (error:any) =>{
    //   if(error.status===400 || error.status===409)
    //   {
    //     alert(error.error.errorMessage);
    //   }
    // }
  );
  }
}
}








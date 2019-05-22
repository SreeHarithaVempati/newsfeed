import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { RegisterService } from '../service/register.service';
import {Users } from 'src/shared/model/Users';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  private registerService:RegisterService;
  statusCreation:String;
  register:Users=new Users();
  submitted=false;
  error:any;
  option:string;
  
  constructor(
    private formBuilder: FormBuilder,
     regServ:RegisterService,
     private router:Router
  )
   {
      this.registerService=regServ;
        this.statusCreation=".";


        this.registerForm = this.formBuilder.group({
          customerName: ['', [Validators.required,Validators.minLength(5)]],
          mailId: ['', [Validators.required,Validators.email]],
          password: ['', [Validators.required,Validators.minLength(6)]],
          profession: [''],
          
          
      });
     }

     get f(){
       return this.registerForm.controls;
     }
ngOnInit() {
    
}
toLoginPage()
{
  this.router.navigate(['login']);
}



  onSubmit(){
    
    this.submitted=true;
     if(this.registerForm.invalid)
     {
       return;
     }
     this.register.userName= this.registerForm.get('customerName').value;
     this.register.userMailId= this.registerForm.get('mailId').value;
     this.register.userPassword= this.registerForm.get('password').value;
     this.register.userProfession= this.registerForm.get('profession').value;
    this.registerService.createUser(this.register).subscribe(
      data=>{
        console.log("customer details are created")
        
        alert("registered successfully");
        this.router.navigate(['login']);
      },
      error =>{
        if(error.status===409)
        {
          console.log('error:');
          alert("User already exists");
          this.router.navigate(['']);
        }
       
    }
    

    )
    
  }


  
}


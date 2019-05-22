import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { LoginService } from '../service/login.service';
import { login } from 'src/shared/model/login';
import { By } from '@angular/platform-browser';

xdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let service: LoginService;
  let login: login;


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [FormsModule, ReactiveFormsModule, HttpClientModule, RouterTestingModule
      ],
      providers: [LoginService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    service = TestBed.get(LoginService);
    fixture.detectChanges();
  });

  xit('should create login component', () => {
    expect(component).toBeTruthy();
  });


  xit('should create form', () => {
    expect(component.loginForm.contains('mailId')).toBeTruthy();
    expect(component.loginForm.contains('password')).toBeTruthy();
  });

  xit('should create  formcontrols', () => {
    let userMailId = component.loginForm.controls["mailId"];
    userMailId.setValue('');
    expect(userMailId.valid).toBeFalsy();
    userMailId.setValue("log@gmail.com");
    expect(userMailId.valid).toBeTruthy();
    let userPassword = component.loginForm.controls["password"];
    userPassword.setValue('');
    expect(userPassword.valid).toBeFalsy();
    userPassword.setValue("logging");
    expect(component.loginForm.valid).not.toBeFalsy();
  });


  xit('form submission test', () => {
    spyOn(component, 'validateLogin').and.callFake(() => {
      service.ValidateUser(login);
    });
    let spy = spyOn(service, 'ValidateUser').and.returnValue({ subscribe: () => { return 'Success' } });
    let email = component.loginForm.get('mailId');
    email.setValue('test@123.com');
    let password = component.loginForm.get('password');
    password.setValue('Test12345');
    let form = fixture.debugElement.query(By.css('form'));
    form.triggerEventHandler('submit', null);
    fixture.detectChanges();
    expect(spy).toHaveBeenCalled();

  });

});











import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterComponent } from './register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegisterService } from '../service/register.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

xdescribe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RegisterComponent],
      imports: [FormsModule, ReactiveFormsModule, HttpClientModule, RouterTestingModule],
      providers: [RegisterService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  xit('component defined', () => {
    expect(component).toBeDefined();
  })
  xit('checking formcontrols', () => {
    let customerName = component.registerForm.controls["customerName"];
    customerName.setValue("goifghi");
    let mailId = component.registerForm.controls["mailId"];
    mailId.setValue("juu@mail.com");

    let password = component.registerForm.controls["password"];
    password.setValue("sreergyrhy");

    expect(component.registerForm.valid).toBeTruthy();
  })
  xit('password minlength ', () => {
    let customerName = component.registerForm.controls["customerName"];
    customerName.setValue("sree");
    let mailId = component.registerForm.controls["mailId"];
    mailId.setValue("sree@mail.com");

    let password = component.registerForm.controls["password"];
    password.setValue("sre");

    expect(component.registerForm.valid).toBeFalsy();
    expect(component.registerForm.controls["password"].valid).toBeFalsy();
    expect(password.errors["required"]).toBeUndefined();
  })
});

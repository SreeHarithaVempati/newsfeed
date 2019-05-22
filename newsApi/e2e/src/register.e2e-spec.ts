import { RegisterPage } from './register.po';
import { HomePage } from './home.po';
import { LoginPage } from './login.po';
import { browser, protractor } from 'protractor';

describe('workspace-project App', () => {
  let registerpage: RegisterPage;
  let loginpage: LoginPage;

  const EC = protractor.ExpectedConditions;


  beforeEach(() => {
    registerpage = new RegisterPage();
    loginpage = new LoginPage();

  });

  it('should display welcome message', () => {
    registerpage.navigateToRegisterPage();
    expect(registerpage.getTitleText1()).toEqual('Register here!');
  });

  it('Should enable register button when all fields are filled and navigate to login page', function () {
    registerpage.navigateToRegisterPage();
    registerpage.setName().sendKeys('monalisa111');
    registerpage.setEmail().sendKeys('monalisa111@gmail.com');
    registerpage.setPassword().sendKeys('123456');
    registerpage.setPhonenumber().sendKeys('7894561230');
    registerpage.setRoles().sendKeys(2);
    registerpage.rbtn().click();
    registerpage.rbtn().click();
    browser.wait(EC.alertIsPresent());
    browser.switchTo().alert().accept();

    browser.wait(EC.visibilityOf(loginpage.getTitleText2()));
    expect(loginpage.getTitleText2().getText()).toContain('Login here');
  });


  it('Should clear all the fields when cancel button is pressed', function () {
    registerpage.navigateToRegisterPage();
    registerpage.cbtn().click();
    expect(registerpage.getTitleText1().getText()).toContain('Register here!');

  });

  it('Should navigate to login page if registered user', function () {
    registerpage.navigateToRegisterPage();
    registerpage.lbtn().click();

    expect(loginpage.getTitleText2().getText()).toContain('Login here');

  });




});

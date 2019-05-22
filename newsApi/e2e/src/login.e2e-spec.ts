import { RegisterPage } from './register.po';
import { HomePage } from './home.po';
import { LoginPage } from './login.po';
import { browser, protractor } from 'protractor';
import { AdminPage } from './admin.po';

xdescribe('workspace-project App', () => {
  let registerpage: RegisterPage;
  let loginpage: LoginPage;
  let homepage: HomePage;
  let adminpage: AdminPage;
  const EC = protractor.ExpectedConditions;


  beforeEach(() => {
    registerpage = new RegisterPage();
    loginpage = new LoginPage();
    homepage = new HomePage();
    adminpage = new AdminPage();
    loginpage.navigateToLoginPage();
  });

  it('should display welcome message', () => {

    expect(loginpage.getTitleText2().getText()).toContain('Login here');
  });

  it('should navigate to home page of news analyst', () => {
    loginpage.setEmail().sendKeys('murthy@gmail.com');
    loginpage.setPassword().sendKeys('123456');
    loginpage.lbtn().click();
    browser.wait(EC.alertIsPresent());
    browser.switchTo().alert().accept();
    expect(homepage.getTitleText1().getText()).toContain('Welcome to News page!');
  });

  it('should navigate to home page of admin', () => {
    loginpage.setEmail().sendKeys('lakshmi@gmail.com');
    loginpage.setPassword().sendKeys('123456');
    loginpage.lbtn().click();
    browser.wait(EC.alertIsPresent());
    browser.switchTo().alert().accept();
    expect(adminpage.getTitleText1().getText()).toContain('News Analysts List');
  });

  it('should navigate to register page when we press back button', () => {
    loginpage.bbtn().click();
    expect(registerpage.getTitleText1().getText()).toContain('Register here!');
  });

});
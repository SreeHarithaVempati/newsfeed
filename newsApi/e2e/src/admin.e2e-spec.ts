
import { LoginPage } from './login.po';
import { browser, protractor } from 'protractor';
import { AdminPage } from './admin.po';

describe('workspace-project App', () => {
  let loginpage: LoginPage;
  let adminpage: AdminPage;
  const EC = protractor.ExpectedConditions;

  beforeEach(() => {

    loginpage = new LoginPage();
    adminpage = new AdminPage();
    loginpage.navigateToLoginPage();
  });

  fit('should display welcome message', () => {

    loginpage.setEmail().sendKeys("lakshmi@gmail.com");
    loginpage.setPassword().sendKeys("123456");
    loginpage.lbtn().click();
    browser.wait(EC.alertIsPresent());
    browser.switchTo().alert().accept();
    browser.wait(EC.visibilityOf(adminpage.getTitleText1()));
    expect(adminpage.getTitleText1().getText()).toContain('News Analysts List');
    expect(adminpage.getTitleText().getText()).toContain('News');

  });

  it('should navigate to login page when logout button is pressed', () => {

    loginpage.setEmail().sendKeys("lakshmi@gmail.com");
    loginpage.setPassword().sendKeys("123456");
    loginpage.lbtn().click();
    browser.wait(EC.alertIsPresent());
    browser.switchTo().alert().accept();
    adminpage.logout().click();
    expect(loginpage.getTitleText2().getText()).toContain('Login here');

  });



}); 

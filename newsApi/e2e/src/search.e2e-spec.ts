import { RegisterPage } from './register.po';
import { HomePage } from './home.po';
import { LoginPage } from './login.po';
import { browser, protractor } from 'protractor';
import { AdminPage } from './admin.po';
import { SearchPage } from './search.po';

fdescribe('workspace-project App', () => {

  let loginpage: LoginPage;
  let homepage: HomePage;
  let searchpage: SearchPage;
  const EC = protractor.ExpectedConditions;


  beforeEach(() => {
    searchpage = new SearchPage();
    loginpage = new LoginPage();
    homepage = new HomePage();

  });

  it("on clicking home button it should navigate to home page of user ", () => {
    searchpage.navigateToSearchPage();
    searchpage.homebutton().click();
    expect(homepage.getTitleText1().getText()).toContain('Welcome to News page!');
  });


  it("on clicking logout button it should navigate to login page  ", () => {
    searchpage.navigateToSearchPage();
    searchpage.logout().click();
    expect(loginpage.getTitleText2().getText()).toContain('Login here');
  });


})

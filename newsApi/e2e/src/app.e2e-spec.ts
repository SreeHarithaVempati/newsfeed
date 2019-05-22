import { browser, by, element, ExpectedConditions } from 'protractor';
import { RegisterPage } from './register.po';
import { HomePage } from './home.po';
import { LoginPage } from './login.po';
import { SearchPage } from './search.po';
import { AdminPage } from './admin.po';


fdescribe('workspace-project App', () => {
  let registerpage: RegisterPage;
  let loginpage: LoginPage;
  let searchpage: SearchPage;
  let adminpage: AdminPage;
  let homepage: HomePage;



  beforeEach(() => {
    registerpage = new RegisterPage();
    loginpage = new LoginPage();
    searchpage = new SearchPage();
    adminpage = new AdminPage();
    homepage = new HomePage();
    registerpage.navigateToRegisterPage();

  });

  it('should display logo text on navbar ', () => {
    registerpage.navigateToRegisterPage();
    expect(registerpage.getTitleText()).toEqual('News');
  });

  it('should display logo text on navbar', () => {
    loginpage.navigateToLoginPage();
    expect(loginpage.getTitleText()).toEqual('News');
  });
  it('should display logo text on navbar', () => {
    searchpage.navigateToSearchPage();
    expect(searchpage.getTitleText()).toEqual('News');
  });

  it('should display logo text on navbar', () => {
    homepage.navigateToHomePage();
    expect(homepage.getTitleText()).toEqual('News');
  });
})

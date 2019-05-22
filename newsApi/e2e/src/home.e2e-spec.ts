
import { HomePage } from './home.po';
import { LoginPage } from './login.po';
import { browser, protractor } from 'protractor';
import { SearchPage } from './search.po';

xdescribe('workspace-project App', () => {
  let loginpage: LoginPage;
  let homepage: HomePage;
  let searchpage: SearchPage;
  const EC = protractor.ExpectedConditions;

  beforeEach(() => {
    homepage = new HomePage;
    loginpage = new LoginPage();
    searchpage = new SearchPage();

  });
  it('should display welcome message', () => {
    homepage.navigateToHomePage();
    expect(homepage.getTitleText1().getText()).toContain('Welcome to News page!');
  });

  it("entered search word shuld display the related cards on the same page", () => {
    loginpage.navigateToLoginPage();
    loginpage.setEmail().sendKeys("murthy@gmail.com");
    loginpage.setPassword().sendKeys("123456");
    loginpage.lbtn().click();
    browser.wait(EC.alertIsPresent());
    browser.switchTo().alert().accept();
    homepage.navigateToHomePage();
    homepage.setSearchWord().sendKeys("hellooo");
    homepage.searchbtn().click();
    expect(homepage.getCards().isDisplayed()).toBeTruthy();
    homepage.logout().click();
    browser.wait(EC.visibilityOf(loginpage.getTitleText2()));
    expect(loginpage.getTitleText2().getText()).toContain('Login here');

  });

  it("should enter the searched history page when clicked searched history button", () => {
    loginpage.navigateToLoginPage();
    loginpage.setEmail().sendKeys("murthy@gmail.com");
    loginpage.setPassword().sendKeys("123456");
    loginpage.lbtn().click();
    browser.wait(EC.alertIsPresent());
    browser.switchTo().alert().accept();
    homepage.searchHistory().click();
    expect(searchpage.getTitleText1().getText()).toContain('Searched Words History');
  });




});









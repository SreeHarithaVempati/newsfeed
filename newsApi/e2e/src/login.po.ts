import { browser, by, element } from 'protractor';

export class LoginPage {

  navigateTo() {
    return browser.get('/');
  }
  navigateToLoginPage() {
    return browser.get('/login');
  }
  getTitleText() {
    return element(by.css('h2')).getText();
  }
  getTitleText2() {
    return element(by.css('h1'));
  }
  lbtn() {
    return element(by.css('#loginbutton'));
  }
  setEmail() {
    return element(by.css('#mailId'));
  }
  setPassword() {
    return element(by.css('#password'));
  }

  bbtn() {
    return element(by.css('#backbtn'));
  }

}
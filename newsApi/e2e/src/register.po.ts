import { browser, by, element } from 'protractor';

export class RegisterPage {
  pwd = {
    password: 'abcdef123',

  };


  navigateToRegisterPage() {
    return browser.get('/');
  }


  getTitleText() {
    return element(by.css('h2')).getText();
  }

  getTitleText1() {
    return element(by.css('h1'));
  }

  setName() {
    return element(by.css('#customerName'));
  }
  setEmail() {
    return element(by.css('#mailId'));
  }
  setPassword() {
    return element(by.css('#password'));
  }
  setPhonenumber() {
    return element(by.css('#phonenumber'));
  }
  setRoles() {
    return element(by.css('#roles'));
  }
  rbtn() {

    return element(by.id('regbtn'));
  }
  cbtn() {
    // console.log(element(by.css('app-root #regbtn')).isEnabled());
    // browser.pause(300000);
    return element(by.css(' #cancelbtn'));
  }

  lbtn() {
    return element(by.css('#loginbtn'));
  }
}

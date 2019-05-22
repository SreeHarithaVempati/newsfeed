import { browser, by, element } from 'protractor';

export class AdminPage {

  navigateTo() {
    return browser.get('/');
  }
  navigateToAdminPage() {
    return browser.get('/admin');
  }
  getTitleText() {
    return element(by.css('h2'));
  }
  getTitleText1() {
    return element(by.css('h4'));
  }
  logout() {
    return element(by.css('#logout'));
  }


}
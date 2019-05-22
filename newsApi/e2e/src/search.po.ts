import { browser, by, element } from 'protractor';

export class SearchPage {

  navigateTo() {
    return browser.get('/');
  }
  navigateToSearchPage() {
    return browser.get('/search');
  }
  getTitleText() {
    return element(by.css('h2')).getText();
  }
  getTitleText1() {
    return element(by.css('h4'));
  }
  homebutton() {
    return element(by.css('#home'));
  }
  logout() {
    return element(by.css('#logout'));
  }


}
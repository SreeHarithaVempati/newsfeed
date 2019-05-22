import { browser, by, element } from 'protractor';

export class HomePage {
  navigateTo() {
    return browser.get('/');
  }
  navigateToHomePage() {
    return browser.get('/home');
  }
  getTitleText() {
    return element(by.css('h2')).getText();
  }
  getTitleText1() {
    return element(by.css('h1'));
  }

  setSearchWord() {
    return element(by.css('#searchKey'));
  }
  getCards() {
    return element(by.css('#card'));
  }
  searchbtn() {
    return element(by.css('#sbtn'));
  }
  logout() {
    return element(by.css('#logoutbtn'));
  }

  searchHistory() {
    return element(by.css('#historyBtn'));
  }

}

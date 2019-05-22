import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SearchedWords } from 'src/shared/model/SearchedWords';
import { NewsApiService } from '../service/news-api.service';
import { LoginService } from '../service/login.service';
import { RegisterService } from '../service/register.service';
import { TokenStorageService } from '../service/token-storage.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  searchObject: SearchedWords;
  variable: any;
  mail: String;
  searchHistory:any;
  router: Router;
  articles:any;
  y:BigInteger;

  constructor(router: Router, 
    private registerService: RegisterService,
    private newsapi: NewsApiService,private tokenStorage:TokenStorageService, 
    private loginService: LoginService ) {
    this.y=registerService.userId;

  }
 
  ngOnInit() {
 
    this.mail = window.sessionStorage.getItem('AuthUsername');
    console.log(this.mail);
    // console.log(this.registerService.userId);
   this.getSearchHistory();
  }

  // searchdisplay() {

  //   this.searchObject = new SearchedWords;
  //   this.variable = this.searchObject.searchWordName;
  // }

  home() {
    this.router.navigate(['register']);
  }
  logout() {
    this.router.navigate(['login']);
  }

  deletesearch(searchWordId) {
    this.newsapi.deleteSearch(searchWordId).subscribe(
      data => {
        console.log("customer details are created");
        console.log(data);
        this.searchHistory = data;
        this.getSearchHistory();
      }
    );
  }

  getSearchHistory() {

    console.log(this.registerService.userId);
    this.newsapi.getSearchHistory(this.tokenStorage.getId()).subscribe(
      
      data => {
        console.log(this.registerService.userId);
        console.log("customer details are created");
        this.searchHistory = data;
        console.log(this.searchHistory);
      }
    )
  }


}

// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { login } from 'src/shared/model/login';
// import { SearchedWords } from 'src/shared/model/SearchedWords';

// @Injectable({
//   providedIn: 'root'
// })
// export class SearchService {
//   http: HttpClient;
//   statusRes: String;
//   mailId: String;
//   loginObject: login;

//   constructor(h: HttpClient) {
//     this.http = h;
//   }

//   public searchWord(searchObject: SearchedWords) {
//     console.log("Hii")
//     console.log("Register service");
//     console.log(searchObject.searchWordName);
//     this.mailId = this.loginObject.userMailId;

//     return this.http.get("http://localhost:6854/SearchedWordsList/" + this.mailId);

//   }
// }

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SearchedWords } from 'src/shared/model/SearchedWords';
import { Users } from 'src/shared/model/Users';

@Injectable({
  providedIn: 'root'
})
export class NewsApiService {

  api_key = '895fb1c46a664c66a851ce0ce0107cd3';
  href='http://localhost:6854/';
  

  constructor(private http:HttpClient) { }

  initSources(){
	 return this.http.get('https://newsapi.org/v2/sources?language=en&apiKey='+this.api_key);
  }

  initArticles(){
   return this.http.get('https://newsapi.org/v2/top-headlines?sources=the-telegraph&apiKey='+this.api_key);
  }
   
  searchArticles(sKey){

    return this.http.get('https://newsapi.org/v2/everything?q='+sKey+'&sortBy=popularity&apiKey='+this.api_key);
  }
  searchSource(source){
    return this.http.get('https://newsapi.org/v2/top-headlines?sources='+source+'&apiKey='+this.api_key);
  }

  storeSearch(data:SearchedWords){
    console.log(data);
    
    return this.http.post(this.href+"StoreSearchedWord",data);
  }
  getSearchHistory(userId){
    console.log(userId);
    return this.http.get(this.href+"SearchedWordsList/"+userId);
  }
  deleteSearch(searchid){
    return this.http.delete(this.href+"DeleteSearchedWord/"+searchid);
  }
  searchAnalyst(mailId){
    return this.http.get<Users>(this.href+"searchNewsAnalyst/"+mailId);
  }
  deleteAnalyst(mailId){
    console.log(mailId);
    
    return this.http.delete(this.href+"DeleteNewsAnalyst/"+mailId);
  }
  getAllAnalyst(){
    return this.http.get<Array<Users>>(this.href+"getAllNewsAnalyst");
  }
  getAllBlockedAnalyst(){
    return this.http.get<Array<Users>>(this.href+"getAllBlockedNewsAnalyst");
  }
  reactivateAnalyst(mailId){
    console.log(mailId);
    
    return this.http.delete(this.href+"reactivateNewsAnalyst/"+mailId);
  }

}
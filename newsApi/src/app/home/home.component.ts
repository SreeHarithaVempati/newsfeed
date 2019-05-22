import { Component, OnInit } from '@angular/core';
import { Location } from "@angular/common";
import { NewsApiService } from '../service/news-api.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SearchedWords } from 'src/shared/model/SearchedWords';
import { LoginService } from '../service/login.service';
import { Users } from 'src/shared/model/Users';
import { RegisterService } from '../service/register.service';

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


	logOut() {
		alert("logged out");
		console.log("logged out");
		window.sessionStorage.clear();
		this.router.navigate(['']);
	}

	searchwords = new SearchedWords();
	user = new Users();

	mArticles: Array<any>;
	mSources: Array<any>;
	source: String;
	search: string;
	noNews: boolean = false;
	searchart: FormGroup;
	// articleLength:number;
	constructor(
		private newsapi: NewsApiService,
		private loginService: LoginService,
		private registerService: RegisterService,
		private location: Location,
		private formBuilder: FormBuilder,
		private router: Router) {
		this.searchart = this.formBuilder.group({


			searchKey: ['', Validators.required],
		})
	}

	get f() { return this.searchart.controls; }



	ngOnInit() {
		console.log(this.registerService.userId);
		//load articles
		this.newsapi.initArticles().subscribe(data => this.mArticles = data['articles']);
		//load news sources
		this.newsapi.initSources().subscribe(data => this.mSources = data['sources']);
	}




	searching(searchKey) {
		console.log(searchKey);
		if (searchKey == null) {
			alert("Please enter the Search Article");

		}
		else {
			this.searchwords.searchWordName = searchKey;
			this.user.userMailId=sessionStorage.getItem("AuthUsername")	;
			this.searchwords.user=this.user;
			console.log(this.searchwords);

			this.newsapi.storeSearch(this.searchwords).subscribe(data => {
			console.log(data);
            },
			);
			this.newsapi.searchArticles(searchKey).subscribe(data => {

				console.log(searchKey);
				this.mArticles = data['articles'];
			
			}, error => {
				if (error.status == 400) {
					this.noNews = true;
				}
			}
			);
			if (this.mArticles.length == 0) {
				this.noNews = true;
			}
			else {
				this.noNews = false;
			}
		}
	}
	searchSource(search) {

		console.log(search);

		this.newsapi.searchSource(search).subscribe(data => {

			this.mArticles = data['articles'];
		},
		);

		if (this.mArticles.length == 0) {
			this.noNews = true;
		}


	}
	navigate() {
		this.router.navigate(['search']);

	}
	logout() {
		
		window.sessionStorage.clear();
	
		this.router.navigate(['login']);
	}


}









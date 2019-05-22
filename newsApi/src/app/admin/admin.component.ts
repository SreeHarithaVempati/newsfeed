import { Component, OnInit } from '@angular/core';
import { NewsApiService } from '../service/news-api.service';
import { LoginService } from '../service/login.service';
import { Users } from 'src/shared/model/Users';
import { Router } from '@angular/router';
import { TokenStorageService } from '../service/token-storage.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  title: any;
  list: Array<Users>;
  disabled: any = false;
  userName:string;
  deactivate: string = "Deactivate";
  deactivated: string = "Deactivated";
  reactivate: string = "Reactivate";
  reactivated: string = "Reactivated";
  j:number;

  constructor(private tokenstorage:TokenStorageService,  private newsApiService: NewsApiService, private loginService: LoginService, private router: Router) { }

  ngOnInit() {
    this.newsApiService.getAllAnalyst().subscribe(data => {

      this.list = data;
      console.log(this.list);
     

    })

  }

  searchAnalyst(userMailId) {

    this.newsApiService.searchAnalyst(userMailId).subscribe(
      data => {
        console.log(data);

      }
    )
  }
  deleteAnalyst(userMailId,j) {
    console.log(userMailId);
    this.list.splice(j,1);
    this.newsApiService.deleteAnalyst(userMailId).subscribe();

  }

  logout() {
    window.sessionStorage.clear();
    this.router.navigate(['login']);
  }



  getBlockedAnalyst() {
    this.newsApiService.getAllBlockedAnalyst().subscribe(data => {

      this.list = data;
      console.log(this.list);

    })


  }

  reactivateAnalyst(userMailId,j) {
    console.log(userMailId);
    this.list.splice(j,1);
    this.newsApiService.reactivateAnalyst(userMailId).subscribe();


  }




}

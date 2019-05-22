import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { RegisterService } from './service/register.service';
import { LoginComponent } from './login/login.component';
// import { AngularFontAwesomeModule } from 'angular-font-awesome';

import { AdminComponent } from './admin/admin.component';
import { SearchComponent } from './search/search.component';
import { httpInterceptorProviders } from './auth-interceptor';
import { FilterPipe } from './filter.pipe';
import { TokenStorageService } from './service/token-storage.service';
import { SampleComponent } from './sample/sample.component';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    HomeComponent,
    LoginComponent,


    AdminComponent,
    SearchComponent,
    FilterPipe,
    SampleComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule, HttpClientModule,
    // AngularFontAwesomeModule
  ],
  providers: [httpInterceptorProviders,TokenStorageService],
  bootstrap: [AppComponent]
})
export class AppModule {

}

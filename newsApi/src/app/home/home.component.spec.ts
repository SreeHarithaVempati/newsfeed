import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeComponent } from './home.component';
import { NewsApiService } from '../service/news-api.service';
import { HttpClientModule } from '@angular/common/http';

import { By } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

xdescribe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  let service: NewsApiService;


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [HomeComponent],
      imports: [HttpClientModule, ReactiveFormsModule, FormsModule, RouterTestingModule],
      providers: [NewsApiService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    service = TestBed.get(NewsApiService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  xit('should show searched data articles', () => {
    let data = {
      articles: ['apple', 'orange', 'mango'],
      totalResults: 3
    };

    let spy = spyOn(service, 'searchArticles').and.returnValue(of(data));
    spyOn(component, 'searching').and.callFake(() => {
      service.searchArticles({});
    });
    fixture.detectChanges();
    component.search = 'apple';
    fixture.detectChanges();
    let form = fixture.debugElement.query(By.css('#sbtn'));
    form.triggerEventHandler('click', null);

    expect(spy).toHaveBeenCalled();
  });

  xit('should show searched data sources', () => {
    let data = {
      articles: ['hindu', 'times of india', 'deccan chronicle'],
      totalResults: 3
    };

    let spy = spyOn(service, 'searchSource').and.returnValue(of(data));
    spyOn(component, 'searchSource').and.callFake(() => {
      service.searchSource({});
    });
    fixture.detectChanges();
    component.source = 'hindu';
    fixture.detectChanges();
    let form = fixture.debugElement.query(By.css('#sourcesearch'));
    form.triggerEventHandler('submit', null);
    expect(spy).toHaveBeenCalled();
  });







});

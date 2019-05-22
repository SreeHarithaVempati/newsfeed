import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchComponent } from './search.component';
import { NewsApiService } from '../service/news-api.service';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { compilePipe } from '@angular/core/src/render3/jit/pipe';

xdescribe('SearchComponent', () => {
  let component: SearchComponent;
  let fixture: ComponentFixture<SearchComponent>;
  let service: NewsApiService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SearchComponent],
      imports: [HttpClientModule, ReactiveFormsModule, FormsModule, RouterTestingModule],
      providers: [NewsApiService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchComponent);
    component = fixture.componentInstance;
    service = TestBed.get(NewsApiService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  xit('should get searchistory', () => {
    let searchhistory = ['bts', 'shinee', 'blackpink', 'exo'];
    spyOn(component, 'getSearchHistory').and.callFake(() => {
      service.getSearchHistory("analyst1@123.com").subscribe(data => {
        component.searchHistory = data;
      });
    });
    let spy = spyOn(service, 'getSearchHistory').and.returnValue(of(searchhistory));
    component.getSearchHistory();
    expect(spy).toHaveBeenCalled();
    expect(component.searchHistory.length).toEqual(4);
  });

  xit('should delete particular searchedword', () => {

    spyOn(component, 'deletesearch').and.callFake(() => {
      service.deleteSearch(15);
    });
    let spy = spyOn(service, 'deleteSearch').and.returnValue(of(true));
    component.deletesearch(12);
    expect(spy).toHaveBeenCalled();
  });


});

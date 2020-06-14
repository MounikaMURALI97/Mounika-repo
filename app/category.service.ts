import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Category } from './category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http:HttpClient) { }
  getCategory() :Observable<any>{
    return this.http.get<any>("http://localhost:8080/category");
  }
  deleteCategory(cid: number) : Observable<any>{
    return this.http.get<any>("http://localhost:8080/deletecategory/"+cid);
  }
  saveCategorytDetails(category: Category) : Observable<any>{
    return this.http.post<any>("http://localhost:8080/savecategory/",category);
  }
  getCategoryById(cid: number): Observable<any> {
    return this.http.get<any>("http://localhost:8080/categoryById/"+cid);
  }
 
}

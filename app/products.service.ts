import { Injectable } from '@angular/core';
import { Products } from './products';
import {Review} from './review';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Category } from './category';
@Injectable({
  providedIn: 'root'
})
export class ProductsService {
 
  id:number;
  public productObj:any;
  reviewObj:any[];
  deleteProduct(pid: number) : Observable<any>{
    return this.http.get<any>("http://localhost:8080/deleteproduct/"+pid);
  }
 
  saveProductDetails(product: Products) : Observable<any>{
    return this.http.post<any>("http://localhost:8080/saveproduct/",product);
  }
  
  saveProductImage(uploadImageData: FormData,pid:number) : Observable<any> {
    return this.http.post<any>("http://localhost:8080/updateproductImage/"+pid,uploadImageData);
  }
 
  
  getProductById(pid: number) : Observable<any>{
    return this.http.get<any>("http://localhost:8080/productById/"+pid);
  }
 
  getProducts() {
    return this.http.get<any>("http://localhost:8080/products");
  }
  
  getProductsByCategory(cid: number) :Observable<any> {
    if(!cid) {
      return of([]);
  }
  return this.http.get<any>("http://localhost:8080/category/"+cid);
  }
  
  public getProductsByName(productName: string) :Observable<any> {
    if(!productName) {
        return of([]);
    }
    return this.http.get<any>("http://localhost:8080/product/"+productName);
}
public getSelectedProduct(pid: number)
{
  
 return this.http.get<Products>("http://localhost:8080/products/"+pid);
}

public getReview(idd:number)
{
 return this.http.get<Review[]>("http://localhost:8080/review/"+idd);
}

callProductPageComponent(pid:number)
  {
     this.id=pid;
    let response=this.getSelectedProduct(pid);
    this.productObj=response;
  }
  getProductByyId()
  {
    return this.productObj;
  }

  addReview(reviewObj,userId,pid)
  {

                          
    return this.http.post<Review>("http://localhost:8080/addReview/"+userId+"/pid/"+pid,reviewObj);
  }

  constructor(private http:HttpClient) { }
}





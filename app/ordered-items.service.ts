import { Injectable } from '@angular/core';
import { Ordereditems } from './ordereditems';
import { Products } from './products';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class OrderedItemsService {

  constructor(private http:HttpClient) { }

  getOrderedItems()
  {
    return this.http.get<Ordereditems[]>("http://localhost:8080/getOrderedItems");
  }
  updateOrderStatus(OrderedItems)
  {
    return this.http.post<Ordereditems>("http://localhost:8080/updateOrderStatus",OrderedItems);
  }
  getOrderedProducts()
  {
    return this.http.get<Products[]>("http://localhost:8080/getOrderedProducts");
  }

  updateProduct(OrderedItems,id)
  {
    return this.http.post<Products>("http://localhost:8080/updateProduct/"+id,OrderedItems);
  }





}

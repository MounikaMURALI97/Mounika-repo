import { Injectable } from '@angular/core';
import { Products } from './products';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ordereditems } from './ordereditems';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {
  cancelOrder(orderId: number):Observable<any> {
    return this.http.get<any>("http://localhost:8080/deleteOrder/"+orderId);
  }
  getOrderedItemsList(userId: any) :Observable<any>{
    return this.http.get<any>("http://localhost:8080/orders/"+userId);    
  }
  

  constructor(private http:HttpClient) { }
  getPaymentTypes() {
    return this.http.get<any>("http://localhost:8080/paymentypes");
  }

  placeOrder(userId,product: Products,quantity:number,paymentTypeId:number): Observable<any> {
    
    return this.http.post<any>("http://localhost:8080/order/"+userId+"/orderQuantity/"+quantity+"/paymentMethod/"+paymentTypeId,product);
  }
}

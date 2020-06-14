import { Injectable } from '@angular/core';
import { Products } from './products';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ProductpageComponent } from './productpage/productpage.component';
import { User } from './user';
import { ShoppingCartItem } from './shopping-cart-item';
import { ShoppingCart } from './shopping-cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {


  userDetails = sessionStorage.getItem('userobject');
  parsedjsonUser=JSON.parse(this.userDetails);
  id:number;
  user=new User();
 
  constructor(private http:HttpClient) { }

  setUser(u)
  {
    this.user=u;
    this.id=u.userId;
  }
  public addToCart(n:number,uobj: User)
  {
    this.user=uobj;
    return this.http.post<Products>("http://localhost:8080/addToCart/"+n,uobj);
  }

  getUserCartAmount()
  {
    return this.http.get<ShoppingCart>("http://localhost:8080/getUserCartAmount/"+this.id);
  }

  getUserCartItem()
  {
    return this.http.get<Products[]>("http://localhost:8080/getUserCartProducts/"+this.id);
  }

  removeProductFromCart(pid)
  {
    return this.http.post<Products>("http://localhost:8080/removeProductFromCart/"+pid,this.user);
  }




}

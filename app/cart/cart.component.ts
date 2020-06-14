import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from '../cart.service';
import {User} from '../user';
import { ShoppingCartItem } from '../shopping-cart-item';
import { ShoppingCart } from '../shopping-cart';
import { Products } from '../products';
import { UserService } from '../user.service';
import { Address } from '../address';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  userDetails = sessionStorage.getItem('userobject');
  parsedjsonUser=JSON.parse(this.userDetails);
  shoppingcartObj=new ShoppingCart();
  shoppingcartItem=new ShoppingCartItem();
  address:Address;
  prod: Array<Products>;
  productsRecieved: Array<Products>;
  constructor(private cartService:CartService,private userService:UserService,private router:Router) { }

  ngOnInit() {
    this.totalAmount();
    this.productList();
  }
  totalAmount()
  {
    this.cartService.getUserCartAmount()
     .subscribe(data=>{
      this.shoppingcartObj.cartTotal=data.cartTotal;
    });

  }
  productList()
  {
    this.cartService.getUserCartItem()
     .subscribe(productsList=>{
      //console.log(productsList);
      this.prod = new Array<Products>();
      this.productsRecieved = productsList;
      
      for (const product of this.productsRecieved) {
        
        const productwithRetrievedImageField = new Products();
        productwithRetrievedImageField.pid = product.pid;
        productwithRetrievedImageField.pname = product.pname;
        productwithRetrievedImageField.image = 'data:image/jpg;base64,' + product.image;
        
        productwithRetrievedImageField.availabilbity = product.availabilbity;
        let a=product.price-((product.offer/100.0)*product.price);
        productwithRetrievedImageField.price = a;
        productwithRetrievedImageField.description = product.description;
        productwithRetrievedImageField.offer = product.offer;  
        this.prod.push(productwithRetrievedImageField);
    }
      console.log("user cart items");
    });
  }
  removeFromCart(pid)
  {
    this.cartService.removeProductFromCart(pid).subscribe(data=>{
      console.log('product deleted');
      this.totalAmount()
      this.productList();
      this.router.navigate(['cart']);
    });
  }
  redirectToPayment(currentProduct:Products)
  {
    this.userService.getAddressById(this.parsedjsonUser.userId).subscribe(
      address=>
        {
          this.address=address;
        },error=>
        {
          if(this.address==null)
          {
            this.router.navigate(['deliverylocation']),
            console.log("set delivery location to order");
          }
          
    });
    sessionStorage.setItem('currentproduct', JSON.stringify(currentProduct));
    this.router.navigate(['payment']);
  }





  logout()
  {
    sessionStorage.clear();    
    this.router.navigate(['']);
    console.log("logged out successfully");
  }
  

}

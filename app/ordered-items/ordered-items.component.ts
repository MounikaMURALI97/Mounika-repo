import { Component, OnInit } from '@angular/core';
import {OrderedItemsService} from '../ordered-items.service'
import { Ordereditems } from '../ordereditems';
import { Router } from '@angular/router';
import { Products } from '../products';

@Component({
  selector: 'app-ordered-items',
  templateUrl: './ordered-items.component.html',
  styleUrls: ['./ordered-items.component.css']
})
export class OrderedItemsComponent implements OnInit {
  prod: Array<Products>;
  productsRecieved: Array<Products>;

OrderedItems:Ordereditems[];
  constructor(private OrderedItemsService:OrderedItemsService,private router:Router) { }

  ngOnInit() {
   this.orderItems();
    this.productList();
   
  }
  orderItems()
  {
    this.OrderedItemsService.getOrderedItems()
    .subscribe(data=>{
     this.OrderedItems=data;
   });
  }
  productList()
  {

    this.OrderedItemsService.getOrderedProducts()
    .subscribe(productsList=>
      {
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
      console.log("order items");

      });

  }
  updateOrderStatus(OrderedItems)
  {
    this.OrderedItemsService.updateOrderStatus(OrderedItems)
    .subscribe(productsList=>
      {
        console.log('order conformed');
        this.orderItems();
    this.productList();
      });
  }
  updateProduct(OrderedItems,id)
  {
    this.OrderedItemsService.updateProduct(OrderedItems,id)
    .subscribe(data=>
      {
        console.log('order canceled');
        this.orderItems();
        this.productList();
      });

  }
  logout()
  {
    sessionStorage.clear();    
    this.router.navigate(['adminlogin']);
    console.log("logged out successfully")
  }


}

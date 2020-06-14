import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { Observable } from 'rxjs';
import { ProductsService } from '../products.service';
import { CartService } from '../cart.service';
import {DomSanitizer,  SafeResourceUrl, SafeUrl} from '@angular/platform-browser';
import {User} from '../user';
import { Category } from '../category';
import { Router } from '@angular/router';
import {Review} from '../review';
import { UserService } from '../user.service';
import { Address } from '../address';

@Component({
  selector: 'app-productpage',
  templateUrl: './productpage.component.html',
  styleUrls: ['./productpage.component.css']
})
export class ProductpageComponent implements OnInit {
  products=new Products();
  review:Review[];
  base64Data:any;
  retrievedImage:any;
  address:Address;
  userDetails = sessionStorage.getItem('userobject');
  parsedjsonUser=JSON.parse(this.userDetails);
  currentProductDetails=sessionStorage.getItem('currentproduct');
  parsedjsonCurrentProduct=JSON.parse(this.currentProductDetails);
  reviewObj: Review=new Review();
  errorMessage;
  
  constructor(private productService:ProductsService,private userService:UserService,private cartService:CartService,private router:Router) { }


    ngOnInit() {
    this.productService.getProductByyId().subscribe(
      response => {
        this.base64Data='data:image/jpg;base64,'+response.image;
        const productObj=new Products();
        productObj.pid=response.pid;
        productObj.image=this.base64Data;
        productObj.pname=response.pname;
        productObj.price=response.price;
        productObj.offer=response.offer;
        productObj.availabilbity=response.availabilbity;
        productObj.description=response.description;
        this.products=productObj;
        console.log("productDetail");       
      } 
      );
      this.productService.getReview(this.parsedjsonCurrentProduct.pid).subscribe(
        response => {
          this.review=response;
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
  addToCart(pid):void{
    this.cartService.addToCart(pid,this.parsedjsonUser)
    .subscribe(data=>{
      
      console.log("Product Added successfully");
     this.router.navigate(['cart']);
    },
    error=>{
      
      console.log("Exception occured"), 
      alert("Product already in cart")
      this.router.navigate(['cart']);
    }
    );
  };


  addReview()
  {
    this.productService.addReview(this.reviewObj,this.parsedjsonUser.userId,this.parsedjsonCurrentProduct.pid)
    .subscribe(data=>{
      
      console.log("Product review Added successfully");
    this.router.navigate(['home']);
    });

  }
 
    
  logout()
  {
    sessionStorage.clear();    
    this.router.navigate(['']);
    console.log("logged out successfully");
  }
     
  


  

}

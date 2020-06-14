import { Component, OnInit, Pipe } from '@angular/core';
import { Router } from '@angular/router';
import { Products } from '../products';
import { Observable } from 'rxjs';
import { ProductsService } from '../products.service';
import {DomSanitizer,  SafeResourceUrl, SafeUrl} from '@angular/platform-browser';
import { Category } from '../category';
import { SecurityContext } from '@angular/compiler/src/core';
import { CategoryService } from '../category.service';
import { Address } from '../address';
import { UserService } from '../user.service';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  
  userDetails = sessionStorage.getItem('userobject');
  parsedjsonUser=JSON.parse(this.userDetails);   
  productName:string;  
  categoryList:Category[];
  productsList:Products[];
  address:Address;
  categoryId:number;
  constructor(private cartService:CartService,private userService:UserService,private categoryService: CategoryService,private _router:Router,private productService:ProductsService,private CartService:CartService) {
   
   }

  ngOnInit() {
    this.loadCategory();
    this.loadProducts();
    this.CartService.setUser(this.parsedjsonUser);
    
  }
  loadProducts() {
    this.productService.getProducts().subscribe(
      productsList => {
        this.productsList=productsList;
        for (const product of this.productsList) {
          product.pid=product.pid;
          product.image = 'data:image/jpg;base64,' + product.image;  
      }
        console.log("products list retrieval successful");
      } ,
      error=>
      {
        console.log("products list retrieval unsuccessful");
      }
      );
  }
  loadCategory()
  {
    this.categoryService.getCategory().subscribe(
      categoryList => {
        this.categoryList=categoryList,         
        console.log("category list retrieval successful");
      } ,
      error=>
      {
        console.log("category list retrieval unsuccessful")
      }
      );
  }
  searchProduct()
  {
    
    this.productService.getProductsByName(this.productName).subscribe(
      productsList => {
        this.productsList=productsList;
        for (const product of this.productsList) {
          product.image = 'data:image/jpg;base64,' + product.image;  
      }
        console.log("searched product retrieval successful");     
        
      } ,
      error=>
      {
        console.log("searched product retrieval unsuccessful: not found"); 
      }
      ); 
    
  }

  displayProductsByCategory(categoryId)
  {
    
    this.productService.getProductsByCategory(categoryId).subscribe(
      productsList => {
        this.productsList=productsList;
        for (const product of this.productsList) {
          product.image = 'data:image/jpg;base64,' + product.image;  
      }
        console.log("category products retrieval successful");      
      } ,
      error=>
      {
        console.log("category products retrieval successful: not found");  
      }
      );
  }
  getSelectedProduct(currentProduct:Products)
  {
    sessionStorage.setItem('currentproduct', JSON.stringify(currentProduct));
    this.productService.callProductPageComponent(currentProduct.pid);
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
            this._router.navigate(['deliverylocation']),
            console.log("set delivery location to order");
          }
          
    });
    sessionStorage.setItem('currentproduct', JSON.stringify(currentProduct));
    this._router.navigate(['payment']);
  }
  addToCart(pid):void{
    this.cartService.addToCart(pid,this.parsedjsonUser)
    .subscribe(data=>{
      
      console.log("Product Added successfully");
     
    },
    error=>{
      
      console.log("Exception occured"), 
      alert("Product already in cart")
      
    }
    );
  };
  logout()
  {
    sessionStorage.clear();    
    this._router.navigate(['']);
    console.log("logged out successfully");
  }
  

}



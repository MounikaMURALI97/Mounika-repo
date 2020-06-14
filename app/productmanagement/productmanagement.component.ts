import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { Router } from '@angular/router';
import { ProductsService } from '../products.service';
import { CategoryService } from '../category.service';
import { Category } from '../category';

@Component({
  selector: 'app-productmanagement',
  templateUrl: './productmanagement.component.html',
  styleUrls: ['./productmanagement.component.css']
})
export class ProductmanagementComponent implements OnInit {

  product=new Products();
  currentproduct:Products;
  productsList:Products[]; 
  isShown:boolean=true;
  categoryList:Category[];
  prod:Products[];
  productsRecieved:Products[];
  file:any;
  
  constructor(private categoryService: CategoryService,private _router:Router,private productService:ProductsService) { }

  ngOnInit() {
    this.getProductDetails();
    this.loadCategory();
  }
  getProductDetails() {
    this.productService.getProducts().subscribe(
      productsList => { 
        console.log(this.product);       
        productsList=this.getProductswithImage(productsList);
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
        console.log("category list retrieval unsuccessful");
      }
      );
  }
  
  getProductswithImage(productsList: Products[]) {
    this.prod = new Array<Products>();
    this.productsRecieved = productsList;
    for (const product of this.productsRecieved) {
      
      const productwithRetrievedImageField = new Products();
      productwithRetrievedImageField.pid = product.pid;
      productwithRetrievedImageField.pname = product.pname;      
      productwithRetrievedImageField.image = 'data:image/jpeg;base64,' + product.image;          
      productwithRetrievedImageField.availabilbity = product.availabilbity;
      productwithRetrievedImageField.price = product.price;
      productwithRetrievedImageField.description = product.description;
      productwithRetrievedImageField.offer = product.offer; 
      productwithRetrievedImageField.quantity = product.quantity; 
      if(product.category==null)
      {
        productwithRetrievedImageField.category = new Category();  
      } 
      else
      {
        productwithRetrievedImageField.category = product.category;  
      }
      
      this.prod.push(productwithRetrievedImageField);   
         
    }
  }
  getProduct(productSelected:Products){
    this.productService.getProductById(productSelected.pid).subscribe(
      currentproduct => {
        this.currentproduct=currentproduct,        
        this.isShown=false;
        console.log("selected product retrieval successful");              
      } ,
      error=>
      {
        console.log("selected product retrieval unsuccessful");  
      }
      );  
  }
  
 setCategory(newcategory:Category)
 {  
   this.product.category=newcategory;    
 }
 setUpdatedCategory(currentcategory:Category)
 {
   this.currentproduct.category=currentcategory;  
 }
  saveProductDetails(){
   
    this.productService.saveProductDetails(this.product).subscribe(      
      product => {       
        this.getProductDetails();
        this.file=null;
        console.log("product details saved successfully");                 
       },
       error=>
       {
        console.log("product details not saved successfully");         
       }
       );    
           
  }

 updateProductDetails(){
  
    this.productService.saveProductDetails(this.currentproduct).subscribe(      
      currentproduct => {  
        this.updateProductImage();     
        this.getProductDetails();      
        this.isShown=true; 
        console.log("product details updates successfully");                
       },
       error=>
       {
        console.log("product details not updated successfully"); 
       }
       );   
        
              
  }
  saveImageDetails(event){
    let reader=new FileReader();
    this.file=event.target.files[0];
   
  }
  updateProductImage() {
    if(this.file!=null)
    {
      const uploadImageData = new FormData();
      uploadImageData.append('imageFile', this.file, this.file.name);
      console.log(uploadImageData);
      this.productService.saveProductImage(uploadImageData,this.currentproduct.pid).subscribe(      
        currentproduct => {       
        this.getProductDetails() ,               
        this.isShown=true;  
        this.file=null;                                
        } 
        );
      }
           
  }
  
  deleteProduct(product)
  {
    this.productService.deleteProduct(product.pid).subscribe(product=>{
      this.getProductDetails();
      console.log("product deleted successful")    
          
    },
    error=>{
      console.log("Exception occured")})
  }

 
  logout()
  {
    sessionStorage.clear();    
    this._router.navigate(['adminlogin']);
    console.log("logged out successfully");
  }
 
  
  
  
}

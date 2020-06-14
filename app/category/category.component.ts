import { Component, OnInit } from '@angular/core';
import { Category } from '../category';
import { Router } from '@angular/router';
import { ProductsService } from '../products.service';
import { CategoryService } from '../category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  category=new Category();
  categoryList:Category[]; 
  
  constructor(private _router:Router,private categoryService:CategoryService) { }

  ngOnInit() {
    this.getCategoryDetails();
  }
  getCategoryDetails() {
    this.categoryService.getCategory().subscribe(
      categoryList => {        
        this.categoryList=categoryList,
        console.log("category list retrieval succussful");
      } ,
      error=>
      {
        console.log("category list retrieval unsuccussful");
      }
      );
  }
  createNewCategory()
  {
    this.category=new Category();  
    this.getCategoryDetails();  
  }
  getCategory(categorySelected:Category){
    this.categoryService.getCategoryById(categorySelected.cid).subscribe(
      category => {
        this.category=category,
        this.getCategoryDetails();
        console.log("current selected category retrieval successful"); 
      } ,
      error=>
      {
        console.log("current selected category retrieval unsuccessful");
      }
      );  
  }
  
  saveCategorytDetails(category){
    
    this.categoryService.saveCategorytDetails(category).subscribe(      
      category => {       
        this.getCategoryDetails(),
        console.log("new category saved successful");                            
       } ,
       error=>
       {
        console.log("new category saved unsuccessful");   
       }
       );         
  }
  
  deleteCategory(category)
  {
    this.categoryService.deleteCategory(category.cid).subscribe(product=>{
      this.getCategoryDetails();
      console.log("category deleted successful")    
          
    },
    error=>{
      console.log("Exception occured")})
  }
  logout()
  {
    sessionStorage.clear();    
    this._router.navigate(['adminlogin']);
    console.log("logged out successful");
  }
 

}

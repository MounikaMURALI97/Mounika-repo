import { Component, OnInit } from '@angular/core';
import { ProductsService } from '../products.service';
import { Router } from '@angular/router';
import { Products } from '../products';
import { OrdersService } from '../orders.service';
import { Paymenttype } from '../paymenttype';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  userDetails = sessionStorage.getItem('userobject');
  parsedjsonUser=JSON.parse(this.userDetails);
  currentProductDetails=sessionStorage.getItem('currentproduct');
  parsedjsonCurrentProduct=JSON.parse(this.currentProductDetails);
  product:Products;
  quantity=0;
  totalAmount=0;
  paymentType:Paymenttype;
  selectedPaymentType=new Paymenttype();
  isShown:boolean=false;
  orderStatusMessage:string;
  constructor(private service:ProductsService,private _service:OrdersService,private _router:Router) { }

  ngOnInit() {  
    this.addProductToBill(this.parsedjsonCurrentProduct.pid);
    this.getPaymentTypes();
  }
  getPaymentTypes() {
    this._service.getPaymentTypes().subscribe(
      paymentType=>{
          this.paymentType=paymentType; 
          console.log("payment types retrieval successful");      
      },
      error=>
      {
        console.log("payment types retrieval unsuccessful"); 
      }
    );    
  }
  addProductToBill(pid: number) {
    this.service.getProductById(pid).subscribe(
      product=>{
          this.product=product;   
          console.log("product added to bill successful"); 
      },
      error=>
      {
        console.log("product added to bill unsuccessful");
      }
    );    
  }
 
  
  placeOrder(product:Products)
  {    
    
    this._service.placeOrder(this.parsedjsonUser.userId,product,this.quantity,this.selectedPaymentType.paymentTypeId).subscribe( 
      product=>{    
            
          this.product=product,
          this.orderStatusMessage="Ordered placed successfully";
          console.log("order placed successfully");
      },
      error=>{
        console.log("Exception occured"), this.orderStatusMessage="Ordered not placed: No stock available"
        
      }
      
    ); 
  }

  calculateTotal()
  {
    this.totalAmount=(this.quantity*this.parsedjsonCurrentProduct.price)-(this.quantity*(this.parsedjsonCurrentProduct.price*this.parsedjsonCurrentProduct.offer)/100);
  }
  showCardDetails(paymentTypeName:string)
  {
    if(paymentTypeName.includes("card"))
    {
      this.isShown=true;
    }
    else
    {
      this.isShown=false;      
    }
  }
  logout()
  {
    sessionStorage.clear();    
    this._router.navigate(['']);
    console.log("logged out successfully");
  }

}

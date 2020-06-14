import { Component, OnInit } from '@angular/core';
import { Ordereditems } from '../ordereditems';
import { OrdersService } from '../orders.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ordereddetails',
  templateUrl: './ordereddetails.component.html',
  styleUrls: ['./ordereddetails.component.css']
})
export class OrdereddetailsComponent implements OnInit {

  userDetails = sessionStorage.getItem('userobject');
  parsedjsonUser=JSON.parse(this.userDetails);
  
  constructor(private _service:OrdersService,private _router:Router) { }
  orderedItems:Ordereditems[];
  ngOnInit() {
    this.getOrderedItemsList();
  }

  getOrderedItemsList() {
    this._service.getOrderedItemsList(this.parsedjsonUser.userId).subscribe(
      orderedItems=>{
          this.orderedItems=orderedItems;
          for(const eachOrderditems of this.orderedItems)
          {
            eachOrderditems.orderedProducts.image= 'data:image/jpg;base64,' +  eachOrderditems.orderedProducts.image;  
              if(eachOrderditems.order.orderStatus.toLocaleLowerCase() =="cancelled")
              {
                eachOrderditems.order.orderStatus="cancelled by admin due to unavoidable reasons";               
              }              
          }
          console.log("ordered items retrieval successful");             
      },
      error=>
      {
        console.log("ordered items retrieval unsuccessful"); 
      }
    );    
  }
  cancelOrder(ordereditems:Ordereditems)
  {
    this._service.cancelOrder(ordereditems.order.orderId).subscribe(
      orderedItems=>{
          this.getOrderedItemsList(); 
          console.log("cancelled order");              
      }
    );  
  }
  logout()
  {
    sessionStorage.clear();    
    this._router.navigate(['']);
    console.log("logged out successful");
  }
}

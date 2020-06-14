import { Component, OnInit } from '@angular/core';
import { Address } from '../address';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { User } from '../user';

@Component({
  selector: 'app-deliverylocation',
  templateUrl: './deliverylocation.component.html',
  styleUrls: ['./deliverylocation.component.css']
})
export class DeliverylocationComponent implements OnInit {
  userDetails = sessionStorage.getItem('userobject');
  parsedjsonUser=JSON.parse(this.userDetails);
  address:Address;
  newAddress=new Address();
  errorMessage:string;
  
  constructor(private _service:UserService,private _router:Router) { }

  ngOnInit() {
    
    this.getAddressById(this.parsedjsonUser.userId);
  }
  getAddressById(userId: number) {
    this._service.getAddressById(this.parsedjsonUser.userId).subscribe(
      address=>
        {
          this.address=address;
          console.log("address retrieval successful");          
        },
        error=>{
          alert("set delivery location for product delivery");
          console.log("address retrieval unsuccessful")
        }
        
    );
  }
  updateUserAddress()
  {
    
    if(this.address!=null)
    {
        this._service.updateUserAddress(this.address).subscribe(
          address=>{
          
          console.log("user address update successful");          
        },
        error=>{
          console.log("Exception occured:user address update unsuccessfull"), this.errorMessage="user address update unsuccessfull"})
      
    }
    
  }

  saveUserAddress()
  {
    
    
    if(this.newAddress!=null)
    {     
     
        this._service.saveUserAddress(this.parsedjsonUser.userId,this.newAddress).subscribe(
          newAddress=>{           
          console.log("user address saved successful");   
          this._router.navigate(['home']);       
        },
        error=>{
          console.log("Exception occured:user address save unsuccessfull"), this.errorMessage="user address save unsuccessfull"})
      
    }
    
  }
  
  logout()
  {
    sessionStorage.clear();    
    this._router.navigate(['']);
    console.log("logged out successful");
  }

}

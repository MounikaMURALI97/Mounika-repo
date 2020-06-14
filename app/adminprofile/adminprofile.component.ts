import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

import { Address } from '../address';

@Component({
  selector: 'app-adminprofile',
  templateUrl: './adminprofile.component.html',
  styleUrls: ['./adminprofile.component.css']
})
export class AdminprofileComponent implements OnInit {
  userDetails = sessionStorage.getItem('adminobject');
  parsedjsonUser=JSON.parse(this.userDetails);
  isShown: boolean = false;
  issShown: boolean = false;
  address:Address;
  newAddress=new Address();
  user:User;
  errorMessage:string;
  constructor(private _service:UserService,private _router:Router) { }

  ngOnInit() {
    
    this.getUserById(this.parsedjsonUser.userId);
    this.getAddressById(this.parsedjsonUser.userId);
  }
  getUserById(userId: number) {
    this._service.getUserById(this.parsedjsonUser.userId).subscribe(
        user=>
        {
          this.user=user;    
          console.log("user retrieval successful");    
        },
        error=>
        {
          console.log("user retrieval unsuccessful");
        }
    );
  }
  enable(){
    this.isShown = true;
  }
  enablee(){
    this.issShown = true;
  }
  updateUserDetails()
  {   
    if(this.user!=null)
    {     
      this._service.updateUserDetails(this.user).subscribe(data=>{
        console.log("user update successful"),
        sessionStorage.setItem('adminobject',JSON.stringify(this.user));
        this._router.navigate(['adminhome']);           
      },
      error=>{
        console.log("Exception occured"), this.errorMessage="user update unsuccessful"})
    }   
  }

  getAddressById(userId: number) {
    this._service.getAddressById(this.parsedjsonUser.userId).subscribe(
      address=>
        {
          this.address=address;
          console.log("address retrieval successful");
        },
        error=>{
          console.log("address retrieval successful")
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
          console.log("Exception occured"), this.errorMessage="user address update not successful"})
      
    }
    
  }

  saveUserAddress()
  {   
    
        this._service.saveUserAddress(this.parsedjsonUser.userId,this.newAddress).subscribe(
          newAddress=>{           
          console.log("user address saved successful");    
          this._router.navigate(['adminhome']);      
        },
        error=>{
          console.log("Exception occured"), this.errorMessage="user address save not successful"})
    
  }

  logout()
  {
    sessionStorage.clear();    
    this._router.navigate(['adminlogin']);
    console.log("logged out successful");
  }


}

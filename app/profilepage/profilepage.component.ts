import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profilepage',
  templateUrl: './profilepage.component.html',
  styleUrls: ['./profilepage.component.css']
})

export class ProfilepageComponent implements OnInit {
  userDetails = sessionStorage.getItem('userobject');
  parsedjsonUser=JSON.parse(this.userDetails);
  isShown: boolean = false;
  issShown: boolean = false;
  user:User;
  errorMessage:string;  
  constructor(private _service:UserService,private _router:Router) { }

  ngOnInit() {
    this.getUserById(this.parsedjsonUser.userId);
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
        sessionStorage.setItem('userobject',JSON.stringify(this.user));
        this._router.navigate(['home']);
            
      },
      error=>{
        console.log("Exception occured"), this.errorMessage="user update not successfull"})
    }
    
  }
  logout()
  {
    sessionStorage.clear();    
    this._router.navigate(['']);
    console.log("logged out successfully");
  }

 

}

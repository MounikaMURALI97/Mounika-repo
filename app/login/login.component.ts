import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginService } from '../login.service';
import {User} from '../user';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

   user=new User();
   errorMessage='';
   public currentUser:any;
  constructor(private _service:LoginService,private _router:Router) { }

  ngOnInit() {
  }

  login()
  {
    
    this._service.userLoginFromRemote(this.user).subscribe(data=>{     
      console.log("login successful"),
      this._router.navigate(['/home']),
      sessionStorage.setItem('userobject', JSON.stringify(data))      
    },
    error=>{
      console.log("Exception occured"), this.errorMessage="wrong username/password"})

  }
}

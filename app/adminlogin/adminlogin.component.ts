import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';
import { User } from '../user';

@Component({
  selector: 'app-adminlogin',
  templateUrl: './adminlogin.component.html',
  styleUrls: ['./adminlogin.component.css']
})
export class AdminloginComponent implements OnInit {
  user=new User();
  errorMessage='';
  public currentUser:any;
  constructor(private _service:LoginService,private _router:Router) { }

  ngOnInit() {
  }
  login()
  {
    
    this._service.adminLoginFromRemote(this.user).subscribe(data=>{
      console.log("login successful"),
      this._router.navigate(['/adminhome']),
      sessionStorage.setItem('adminobject', JSON.stringify(data))    
        
    },
    error=>{
      console.log("Exception occured:login unsuccessful"), this.errorMessage="wrong username/password"})

  }
}

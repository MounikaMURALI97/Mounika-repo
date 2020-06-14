import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SignUpService } from '../sign-up.service';
import {User} from '../user';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  user: User=new User();
  errorMessage;
  constructor( private httpClientService:SignUpService,private router:Router) { }

  ngOnInit() {
  }
  createUser():void{
    this.httpClientService.createUser(this.user)
    .subscribe(data=>{
      console.log("Register successful"),
      this.router.navigate(['/home']),
      sessionStorage.setItem('userobject', JSON.stringify(data))
    },
    error=>{
      console.log("Exception occured"), 
      this.errorMessage="EmailId already exist"}
      
    
    );
  };


}








import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'app-usermanagement',
  templateUrl: './usermanagement.component.html',
  styleUrls: ['./usermanagement.component.css']
})
export class UsermanagementComponent implements OnInit {

  users:User[];
  errorMessage:string;
  constructor(private router:Router,private service:UserService) { }

  ngOnInit() {
    this.getUsers();
  }
  getUsers() {
    this.service.getUsers().subscribe(users=>{
      this.users=users,      
      console.log("users retrieved successful");   
          
    },
    error=>{
      console.log("Exception occured"), this.errorMessage="users not retrienved successfull"})
  }
  deleteUser(user:User)
  {
    this.service.deleteUser(user.userId).subscribe(users=>{
      this.getUsers();
      console.log("users deleted successful");   
          
    },
    error=>{
      console.log("Exception occured"), this.errorMessage="users not deleted successfull"})
  }
  logout()
  {
    sessionStorage.clear();    
    this.router.navigate(['adminlogin']);
    console.log("logged out successfully")
  }

}

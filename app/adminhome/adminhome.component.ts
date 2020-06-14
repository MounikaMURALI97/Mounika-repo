import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adminhome',
  templateUrl: './adminhome.component.html',
  styleUrls: ['./adminhome.component.css']
})
export class AdminhomeComponent implements OnInit {
  userDetails = sessionStorage.getItem('adminobject');
  parsedjsonUser=JSON.parse(this.userDetails);
  constructor(private _router:Router) { }

  ngOnInit() {
    
  }
  logout()
  {
    sessionStorage.clear();    
    this._router.navigate(['']);
    console.log("logged out");
  }
}

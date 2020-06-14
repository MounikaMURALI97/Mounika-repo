import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class AuthguardService implements CanActivate{

  constructor(public router: Router) {}

  canActivate(): boolean {
   const token = sessionStorage.getItem('userobject');
    if (!token) {
      this.router.navigate(['']);
      console.log('false');
      return false;
    }
    console.log('true' +token);
    return true;
  }
}

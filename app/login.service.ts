import { Injectable } from '@angular/core';
import { User } from './user';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  adminLoginFromRemote(user: User):Observable<any>{
    return this._http.post<any>("http://localhost:8080/adminlogin",user);
  }

  constructor(private _http:HttpClient) { }

  public userLoginFromRemote(user:User):Observable<any>{
      return this._http.post<any>("http://localhost:8080/login",user);

  }
}

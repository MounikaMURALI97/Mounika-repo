import { Injectable } from '@angular/core';
import { User } from './user';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { Address } from './address';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  deleteUser(userId: number):Observable<any> {
    return this._http.get<any>("http://localhost:8080/deleteuser/"+userId);
  }
  getUsers() :Observable<any>{
    return this._http.get<any>("http://localhost:8080/users");
  }
  saveUserAddress(userId:number,newAddress: Address) :Observable<any>{
    console.log("user address"+userId);
    return this._http.post<any>("http://localhost:8080/saveaddress/"+userId,newAddress);
  }
  
  getAddressById(userId: any) :Observable<any>{
    return this._http.get<any>("http://localhost:8080/address/"+userId);
  }
  updateUserAddress(address: Address):Observable<any> {
    
    return this._http.post<any>("http://localhost:8080/useraddress",address);
  }
  getUserById(userId: number):Observable<any> {
    return this._http.get<any>("http://localhost:8080/user/"+userId);
  }
  updateUserDetails(user: User):Observable<any> {
    return this._http.post<any>("http://localhost:8080/updateUser",user);
  }

  constructor(private _http:HttpClient) { }
}

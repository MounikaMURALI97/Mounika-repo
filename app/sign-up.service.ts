import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  constructor(private httpClient:HttpClient) { }

  public createUser(user)
  {
    return this.httpClient.post<User>("http://localhost:8080/user",user);
  }
}




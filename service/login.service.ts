import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserModel } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = 'http://localhost:7778/user';

  constructor(private http: HttpClient) { }

  getUserByEmailAndPass(email:String, pass:String) : Observable<Object> {
    return this.http.get(`${this.baseUrl}/${email}/${pass}`);
  }

  addUser(user : UserModel) : Observable<Object> {
    return this.http.post(`${this.baseUrl}`, user);
  }

  validateEmail(email:String) : Observable<Object> {
    return this.http.get(`${this.baseUrl}/email/${email}`);
  }

  validateMobile(mobile:String) : Observable<Object> {
    return this.http.get(`${this.baseUrl}/mobile/${mobile}`);
  }
}

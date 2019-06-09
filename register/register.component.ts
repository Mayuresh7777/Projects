import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from '../models/User';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: UserModel;
  validEmail: boolean;
  validMobile: boolean;
  constructor(private routes: Router, private loginService: LoginService) {
    this.user = new UserModel();
    this.validEmail = false;
    this.validMobile = false;
  }

  ngOnInit() {
  }

  signup() {
    console.log("Register Successful");
    this.loginService.addUser(this.user).subscribe(data => console.log(data), error => console.log(error));
    this.user = new UserModel();
    this.routes.navigate(['/login']);
  }

  checkEmail(email: String) {
    console.log("email : " + email);
    this.loginService.validateEmail(email).subscribe(
      data => {
        console.log(data);
        this.setValidEmail(data as boolean);
      }, error => console.log(error));
  }

  checkMobile(mobile: String) {
    this.loginService.validateMobile(mobile).subscribe(
      data => {
        console.log(data);
        this.setValidMobile(data as boolean);
      }, error => console.log(error));
  }

  setValidEmail(flag: boolean) {
    this.validEmail = flag;
  }

  setValidMobile(flag: boolean) {
    this.validMobile = flag;
  }
}

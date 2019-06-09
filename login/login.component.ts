import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from '../models/User';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email: String;
  pass: String;
  valid: boolean;
  @Input() user: UserModel;
  constructor(private routes: Router, private loginService: LoginService) {
  }

  ngOnInit() {
    this.email = "";
    this.pass = "";
    this.valid = false;
  }

  checkLogin() {
    console.log("Login works");
    this.loginService.getUserByEmailAndPass(this.email, this.pass).subscribe(
      data => {
        this.user = data as UserModel;
        if (this.user != null)
          this.validate(this.user);
        else
          this.valid = true;
      },
      error => console.log('ERROR: ' + error));
  }
  validate(user: UserModel) {
    this.user = user;
    console.log(this.user);
    this.routes.navigate(['/home']);
  }
  signup() {
    this.routes.navigate(['/register']);
  }

}

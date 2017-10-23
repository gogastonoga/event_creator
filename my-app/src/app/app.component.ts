import { Component } from '@angular/core';
import { UserService } from './services/user.service';
import { LoginComponent } from './login/login.component';
declare var $: any;
declare var jquery: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Wolimierz Appliaction';
  loginComponent;
  loginButton: boolean = false;
  logoutButton: boolean = true;

  constructor(private _userService: UserService) {
  }

  setLogButtons() {
    if (localStorage.getItem('ULoged') === 'true') {
      this.logoutButton = false;
      this.loginButton = true;
    } else if (localStorage.getItem('ULoged') === 'false') {
      this.logoutButton = true;
      this.loginButton = false;
    }
  }

  logout() {
    this._userService.logout();
    window.location.reload();
  }
}
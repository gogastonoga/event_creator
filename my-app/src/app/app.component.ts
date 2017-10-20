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

  constructor(private _userService: UserService) {
  }

  logout() {
    this._userService.logout();
  }
}
import { Component } from '@angular/core';
import { UserService } from './services/user.service';
declare var $: any;
declare var jquery: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Wolimierz Appliaction';

  constructor(private _userService: UserService) {
  }

  
  logout() {
    console.log("aaa");
    this._userService.logout();
  
  }
}
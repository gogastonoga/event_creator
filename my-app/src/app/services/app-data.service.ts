import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';
import { User } from '../create_user/user';

@Injectable()
export class AppDataService {

  private _createprofileURL = 'http://localhost:8080/wolimierz/users';

  constructor(private http: AuthHttp) {
  }

  getCities() {
    return this.http.get('http://localhost:8080/wolimierz/costsettings').map(res => res.json());
  }

  getUsers() {
    return this.http.get('http://localhost:8080/wolimierz/costsettings').map(res => res.json());
  }

  create(user: User) {
    return this.http.post(this._createprofileURL, user, {
    })
      .map(res => res.json());
  }
}
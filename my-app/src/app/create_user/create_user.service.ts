import {Injectable} from '@angular/core';
import { Http} from '@angular/http';
import {User} from './user';
import { AuthHttp } from 'angular2-jwt';
import 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Injectable()
export class CreateUserService {
    private _createprofileURL = 'http://localhost:8080/wolimierz/users';
    constructor(private http:AuthHttp){

    }
      create(user: User) {
        return this.http.post(this._createprofileURL, user, {
        })
       .map(res =>  res.json());
      }

}
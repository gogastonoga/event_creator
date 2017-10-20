import {Injectable} from '@angular/core';
import { Http} from '@angular/http';
import {User} from './user';
import 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Injectable()
export class CreateUserService {
    private _createprofileURL = 'http://localhost:8080/users';
    constructor(private http:Http){

    }
      create(user: User) {
        return this.http.post(this._createprofileURL, user, {
        })
       .map(res =>  res.json());
      }

}
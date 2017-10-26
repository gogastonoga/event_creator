import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { AuthHttp } from 'angular2-jwt';
import { Contact } from '../contact/contact'
import 'rxjs/Rx';
import 'rxjs/add/operator/map';

@Injectable()
export class MessageService {

    private _contactRequestURL = 'http://localhost:8080/wolimierz/contactrequests';

    constructor(private http: AuthHttp) {
    }

    getContactRequest() {
        return this.http.get(this._contactRequestURL)
            .map(res => <Object>res.json())
            .catch(this.handleError);
    }

    getMessage(id) {
        console.log(id);
        const url = `${this._contactRequestURL}?id=` + id;
        return this.http.get(url)
            .map(res => <Contact[]>res.json())
            .catch(this.handleError);
    }

    private handleError(error: any) {
        console.log('Yup an error occurred', error);
        return Observable.throw(error.message || error);
    }
}
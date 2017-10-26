import { Contact } from './contact';
import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Injectable()
export class ContactService {

    private _contactURL = 'http://localhost:8080/wolimierz/contactrequests';
    constructor(private http: Http) {

    }

    create(contact: Contact) {
        return this.http.post(this._contactURL, contact, {
        })
            .map(res => res.json());
    }


    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}

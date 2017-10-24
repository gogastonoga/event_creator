import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { FormGroup } from '@angular/forms';
import 'rxjs/Rx';
import 'rxjs/add/operator/map';

@Injectable()
export class EventService {

    private _createEventURL = 'http://localhost:8080/wolimierz/events';

    constructor(private http: Http) {
    }

    createEvent(event: Object) {
        return this.http.post(this._createEventURL, event, {
        });
    }

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}
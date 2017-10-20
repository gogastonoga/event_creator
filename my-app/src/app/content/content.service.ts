import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
import 'rxjs/add/operator/map';


@Injectable()
export class ContentService {

    private _contentURL = 'http://localhost:8080/content';
    private _editHomepageURL = 'http://localhost:8080/content/home';

    constructor(private http: Http) {
    }

    getContent() {
        return this.http.get(this._contentURL)
            .map(res => <Object>res.json())
            .catch(this.handleError);
    }

    createEvent(content: Object) {
        return this.http.post(this._editHomepageURL, content, {
        });
    }

    editContentHomePage(content: Object) {
        console.log(content);
                return this.http.put(this._editHomepageURL, content, {
                });
            }

    private handleError(error: any) {
        console.log('Yup an error occurred', error);
        return Observable.throw(error.message || error);
    }
}
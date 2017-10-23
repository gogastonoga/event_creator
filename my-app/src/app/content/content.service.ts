import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { Observable } from 'rxjs/Observable';
import { Form } from '../form/form';
import 'rxjs/Rx';
import 'rxjs/add/operator/map';

@Injectable()
export class ContentService {

    private _contentURL = 'http://localhost:8080/wolimierz/content';
    private _editHomepageURL = 'http://localhost:8080/wolimierz/content/home';
    private _editFormURL = 'http://localhost:8080/wolimierz/content/form';
    private _editSeasonsURL = 'http://localhost:8080/wolimierz/content/seasons';

    constructor(private http: Http, private ahttp: AuthHttp) {
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

    editForm(form: Form) {
        console.log(form);
        return this.ahttp.put(this._editFormURL, form, {
        });
    }

    editSeasons(form) {
        console.log(form);
        return this.ahttp.put(this._editSeasonsURL, form, {
        });
    }

    editContentHomePage(content: Object) {
        return this.ahttp.put(this._editHomepageURL, content);
    }


    private handleError(error: any) {
        console.log('Yup an error occurred', error);
        return Observable.throw(error.message || error);
    }
}
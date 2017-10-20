import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
import 'rxjs/add/operator/map';


@Injectable()
export class CostService {

    private _costSettingsURL = 'http://localhost:8080/costsettings';

    constructor(private http: Http) {
    }

    getCostSettings() {
        return this.http.get(this._costSettingsURL)
            .map(res => <Object>res.json())
            .catch(this.handleError);
    }


    editCostSettings(costSettings: Object) {
        console.log(costSettings);
                return this.http.put(this._costSettingsURL, costSettings, {
                });
            }

    private handleError(error: any) {
        console.log('Yup an error occurred', error);
        return Observable.throw(error.message || error);
    }
}
import { Component, Input, OnInit, OnChanges } from '@angular/core';
import { ContentService } from '../content/content.service';
import { CostService } from '../cost/cost.service';
import { EventService } from '../event/event.service';
import { ReactiveFormsModule, FormsModule, FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { Form, Description } from './form';
import { formEditDate } from './form';
import { editEventTypes } from './form';
import { Summing } from './form';
import { ElementRef, ViewChild } from '@angular/core';
import { Http } from '@angular/http';
import { RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import {
    MatSnackBar, MatSnackBarConfig,
    MatSnackBarHorizontalPosition,
    MatSnackBarVerticalPosition
} from '@angular/material';
import { Dir } from '@angular/cdk/bidi';
import { ViewEncapsulation } from '@angular/core';

@Component({
    selector: 'app-form',
    templateUrl: './form.component.html',
    styleUrls: ['./form.component.css'],
    providers: [ContentService, EventService, DatePipe, Dir]
})
export class FormComponent implements OnInit {

    content;
    errorString: string;
    responseStatus: Object = [];
    eventForm: FormGroup;
    eventArray = [];
    returnMsg;
    contentEventArray = [];
    summing: Summing = { season: '', bounds: '', days: '', date: '' };
    progressBar = 0;
    editForms: boolean = false;
    form: Form;
    formEditDate: formEditDate[] = [{
        name: 'LETNI',
        from: '' + '',
        to: '' + '',
        description: ''
    },
    {
        name: 'ZIMOWY',
        from: '' + '',
        to: '' + '',
        description: ''
    }
    ];
    editEventTypes: editEventTypes[] = [];
    apiEndPoint = 'http://localhost:8080/wolimierz/media/image?parent=event_size&parentId=';
    disabledEdit: boolean = true;
    message: string = 'Wydarzenie zostało utworzone.';
    actionButtonLabel: string = 'Zamknij';
    action: boolean = true;
    setAutoHide: boolean = true;
    autoHide: number = 5000;
    addExtraClass: boolean = true;
    horizontalPosition: MatSnackBarHorizontalPosition = 'center';
    verticalPosition: MatSnackBarVerticalPosition = 'bottom';
    @Input() eventType: editEventTypes;

    descriptions: Description[] = new Array();
    

    constructor(private _contentService: ContentService, private _eventService: EventService, private fb: FormBuilder,
        private datePipe: DatePipe, private _costService: CostService, private http: Http, private ahttp: AuthHttp, private dir: Dir,  public snackBar: MatSnackBar) {
        this.createForm();
    }

    ngOnInit() {
        this.getContent();
        this.form = new Form();
        this.eventType = new editEventTypes();
        if (localStorage.getItem('DEdit') === 'false') {
            this.disabledEdit = false;
        }
    }

    isFirst(): boolean {
        if ($('#first').hasClass('active')) {
            return true;
        } else {
            return false;
        }
    }

    isLast(): boolean {
        if ($('#last').hasClass('active') || $('#last2').hasClass('active')) {
            return true;
        } else {
            return false;
        }
    }

    isSubmit(): boolean {
        if ($('#last').hasClass('active')) {
            return false;
        } else {
            return true;
        }
    }

    isSave(): boolean {
        if ($('#last2').hasClass('active')) {
            return false;
        } else {
            return true;
        }
    }

    isNotEmptyTermin() {
        if (this.eventForm.value.seasonId !== '' || this.eventForm.value.kindOfDays !== '') {
            return true;
        }
    }

    isNotEmptyDate() {
        if (this.eventForm.value.eventTime !== '') {
            return true;
        }
    }

    createForm() {
        this.eventForm = this.fb.group({
            organizer: this.fb.group({
                name: ['', Validators.required],
                surname: ['', Validators.required],
                phoneNumber: ['', Validators.required],
                mail: ['', Validators.required]
            }),
            nights: ['', Validators.required],
            eventTime: '',
            eventTypeIds: '',
            maxCost: ['', Validators.required],
            eventSizeId: ['', Validators.required],
            additionalRequirements: ['', Validators.required],
            kindOfDays: '',
            seasonId: '',
            guestsNumber: '',
            rooms: ''
        });
    }

    next = (): void => {
        const cur = $('.form-panel').index($('.form-panel.active'));
        if (cur !== $('.form-panel').length - 1) {
            $('.form-panel').removeClass('active').hide();
            $('.form-panel').eq(cur + 1).fadeIn().addClass('active');
        }
        this.progressBar += 100 / 6;
    }

    previous = (): void => {
        const cur = $('.form-panel').index($('.form-panel.active'));
        if (cur !== 0) {
            $('.form-panel').removeClass('active').hide();
            $('.form-panel').eq(cur - 1).fadeIn().addClass('active');
        }
        this.progressBar -= 100 / 6;
    }

    select(event) {
        if (event.selected === false) {
            event.selected = true;
            this.contentEventArray.push(event.translation);
            this.eventArray.push(event.globalId);
            this.eventForm.value.eventTypeIds = this.eventArray;
            console.log(this.eventForm.value.eventTypeIds);
        }
        else {
            event.selected = false;
            this.eventArray.pop();
            this.contentEventArray.pop();
            this.eventForm.value.eventTypeIds = this.eventArray;
        }
    }

    addSeason(seas) {
        this.summing.season = seas.name;
    }

    addBound(peop) {
        this.summing.bounds = peop.bounds;
    }

    addDays(days) {
        this.summing.days = days;
    }

    addDate() {
        const convertDate = this.datePipe.transform(this.eventForm.value.eventTime, 'yyyy/MM/dd');
        this.summing.date = convertDate;
    }

    getContent() {
        this._contentService.getContent()
            .subscribe(
            (content: Object) => {
                this.content = content;
                this.parseToDescription(this.content);
                console.log(this.descriptions);
            },
            error => this.errorString = <any>error
            );
        
    }

    parseToDescription(content) {
        console.log(this.descriptions[0]);
        
        for (let i = 0; i < content.formDto.events.length; i++) {
            let description = {
                value: content.formDto.events[i].description
            }
            this.descriptions.push(description);
        }
        console.log(this.descriptions);
        return this.descriptions;
    }

    createEvent() {
        if (this.eventForm.value.kindOfDays === '') {
            this.eventForm.value.kindOfDays = null;
        }
        if (this.eventForm.value.seasonId === '') {
            this.eventForm.value.seasonId = null;
        }
        if (this.eventForm.value.eventTime === '') {
            this.eventForm.value.seasonId = null;
        }
        this.eventForm.value.eventTime = this.datePipe.transform(this.eventForm.value.eventTime, 'yyyy-MM-ddT00:00:00');
        this.eventForm.value.eventTypeIds = this.eventArray;
        const formModel = this.eventForm.value;
        this._eventService.createEvent(formModel).subscribe(
            data => console.log(this.responseStatus = data),
            err => console.log(err),
            () => this.message = 'Wydarzenie zostało utworzone.'
        );
    }

    edit() {
        if (!this.disabledEdit) {
            if (this.editForms) {
                $('.formDescription').prop('readonly', true);
                $('.hint1').slideUp();
                $('.hint2').slideUp();
                this.editForm();
                this.editSeasons();
                this.editEvents();
            } else {
                $('.formDescription').prop('readonly', false);
                $('.hint1').slideDown();
                $('.hint2').slideDown();
            }
        }
    }

    editForm() {
        this._contentService.editForm(this.form).subscribe(
            data => console.log(this.responseStatus = data),
            err => console.log(err)
        );
    }

    editSeasons() {
        for (let i = 0; i < 2; i++) {
            if (this.formEditDate[i].from !== '') {
                this.formEditDate[i].from = this.formEditDate[i].from + 'T00:00:00';
            }
            if (this.formEditDate[i].to !== '') {
                this.formEditDate[i].to = this.formEditDate[i].to + 'T00:00:00';
            }
        }
        console.log(this.formEditDate);
        this._contentService.editSeasons(this.formEditDate).subscribe(
            data => console.log(this.responseStatus = data),
            err => console.log(err)
        );
    }

    editEvents() {
        this._contentService.editEvents(this.editEventTypes).subscribe(
            data => console.log(this.responseStatus = data),
            err => console.log(err)
        );
    }

    onInputChange(event: any) {
        console.log('This is emitted as the thumb slides');
    }

    open() {
        let config = new MatSnackBarConfig();
        config.verticalPosition = this.verticalPosition;
        config.horizontalPosition = this.horizontalPosition;
        config.duration = this.setAutoHide ? this.autoHide : 0;
        config.extraClasses = this.addExtraClass ? ['party'] : undefined;
        config.direction = this.dir.value;
        this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
    }



    onChange(ev, i: number){
        // console.log(ev + "sadsa");
        // if (ev.translation === 'Integracja') {

        // }
        let eventType = {
            description:  this.descriptions[i].value,
            name: '',
            globalId: ev.globalId
        }
       
        this.editEventTypes.push(eventType);
        console.log(this.editEventTypes);
    }

    // createUrl(peop){
    //     console.log(peop + 'aaaaaaaaaaaaaaaaaaaaaa');
    //     this.apiEndPoint = this.apiEndPoint + peop.globalId;
    // }

    getToken() {
        localStorage.getItem('accessToken');
    }

    // fileChange(event) {
    //     let fileList: FileList = event.target.files;
    //     if(fileList.length > 0) {
    //         let file: File = fileList[0];
    //         let formData:FormData = new FormData();
    //         formData.append('uploadFile', file, file.name);
    //         let headers = new Headers();
    //         /** No need to include Content-Type in Angular 4 */
    //         headers.append('Content-Type', 'multipart/form-data');
    //         headers.append('Accept', 'application/json');
    //         let options = new RequestOptions({ headers: headers });
    //         this.ahttp.put(`${this.apiEndPoint}`, formData, options)
    //             .map(res => res.json())
    //             .catch(error => Observable.throw(error))
    //             .subscribe(
    //                 data => console.log('success'),
    //                 error => console.log(error)
    //             )
    //     }
    // }

}


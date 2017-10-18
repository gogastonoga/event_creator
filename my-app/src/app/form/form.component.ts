import { Component, Input, OnInit, OnChanges } from '@angular/core';
import { Event, Organizer } from '../event/event';
import { ContentService } from '../content/content.service';
import { EventService } from '../event/event.service';
import { ReactiveFormsModule, FormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { Summing } from './summing';

@Component({
    selector: 'app-form',
    templateUrl: './form.component.html',
    styleUrls: ['./form.component.css'],
    providers: [ContentService, EventService, DatePipe]
})
export class FormComponent implements OnInit {

    @Input() event: Event;
    @Input() organizer: Organizer;
    showEditDialog = false;
    content;
    errorString: string;
    responseStatus: Object = [];
    returnMsg: String;
    eventForm: FormGroup;
    eventArray = [];
    contentEventArray = [];
    summing: Summing = { season: '', bounds: '', days: '', date: '' };
    progressBar = 0;

    constructor(private _contentService: ContentService, private _eventService: EventService, private fb: FormBuilder,
        private datePipe: DatePipe) {
        this.createForm();
    }

    isFirst(): boolean {
        if ($('#first').hasClass('active')) {
            return true;
        }
    }

    isLast(): boolean {
        if ($('#last').hasClass('active') || $('#last2').hasClass('active')) {
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
            eventTime: ['', Validators.required],
            eventTypeIds: this.returnEvent(),
            maxCost: ['', Validators.required],
            rooms: '',
            eventSizeId: ['', Validators.required],
            additionalRequirements: ['', Validators.required],
            kindOfDays: ['', Validators.required],
            seasonId: ['', Validators.required]
        });
    }


    next = (): void => {
        const cur = $('.form-panel').index($('.form-panel.active'));
        if (cur !== $('.form-panel').length - 1) {
            $('.form-panel').removeClass('active').hide();
            $('.form-panel').eq(cur + 1).fadeIn().addClass('active');
        }
        this.progressBar += 15;
        if (this.progressBar >= 80) {
            this.progressBar = 100;
        }
    }

    previous = (): void => {
        const cur = $('.form-panel').index($('.form-panel.active'));
        if (cur !== 0) {
            $('.form-panel').removeClass('active').hide();
            $('.form-panel').eq(cur - 1).fadeIn().addClass('active');
        }
        this.progressBar -= 15;
        if (this.progressBar === 100) {
            this.progressBar = 90;
        }
    }

    ngOnInit() {
        this.event = new Event();
        this.getContent();
    }

    private disableModal(): void {
        this.showEditDialog = false;
    }

    private displayEditDialog(): void {
        this.showEditDialog = !this.showEditDialog;
    }

    returnEvent() {
        return this.eventArray;
    }

    select(event) {
        if (event.selected === false) {
            event.selected = true;
            this.contentEventArray.push(event.translation);
            this.eventArray.push(event.globalId);
            this.eventForm.value.eventTypeIds = this.eventArray;
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
        console.log(this.summing);
    }

    addBound(peop) {
        this.summing.bounds = peop.bounds;
        console.log(this.summing);
    }

    addDays(days) {
        this.summing.days = days;
        console.log(this.summing);
    }

    addDate() {
        const convertDate = this.datePipe.transform(this.eventForm.value.eventTime, 'yyyy/MM/dd');
        this.summing.date = convertDate;
        console.log(this.summing);
    }

    getContent() {

        this._contentService.getContent()
            .subscribe(
            (content: Object) => {
                this.content = content;
                console.log(this.content.formDto.events);
            },
            error => this.errorString = <any>error
            );
    }

    createEvent() {
        this.eventForm.value.eventTime = this.datePipe.transform(this.eventForm.value.eventTime, 'yyyy-MM-ddT00:00:00');
        this.eventForm.value.eventTypeIds = this.eventArray;
        const formModel = this.eventForm.value;
        console.log(formModel);
        this._eventService.createEvent(formModel).subscribe(
            data => console.log(this.responseStatus = data),
            err => console.log(err),
            () => this.returnMsg = 'Event is created!'
        );
    }

    onInputChange(event: any) {
        console.log('This is emitted as the thumb slides');
    }

}



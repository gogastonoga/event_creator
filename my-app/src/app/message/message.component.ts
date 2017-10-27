import { Component, Input, OnInit, OnChanges, ViewChild } from '@angular/core';
import { Http } from '@angular/http';
import { RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { MessageService } from './message.service';
import { Contact } from "../contact/contact";
import { DatePipe } from '@angular/common';

// export class DateString {
//     dateString: string;
// }
@Component({
    selector: 'app-form',
    templateUrl: './message.component.html',
    styleUrls: ['./message.component.css'],
    providers: [MessageService]
})
export class MessageComponent implements OnInit {

    public sortOrder = 'asc';
    numberOfItems: number;
    limit: number;
    page: number;
    messages;
    dontReadMessages;
    errorString;
    dates: string[] = [];
    i = 0;

    constructor(private _messageService: MessageService) { }

    ngOnInit() {
        this.addCladdActive();
        this.getContactRequest();
        this.getDontReadMessages();
        this.limit = 10;
        this.page = 1;
    }

    nextPage(): void {
        this.page += 1;
    }

    previousPage(): void {
        if (this.page > 1) {
            this.page -= 1;
        }
    }

    all = (): void => {
        $('#third').removeClass('active').hide();
        $('#dont-read').removeClass('active').hide();
        $('#all').fadeIn().addClass('active').show();
    }

    dontRead = (): void => {
        $('#all').removeClass('active').hide();
        $('#third').removeClass('active').hide();
        $('#dont-read').fadeIn().show();
    }

    third = (): void => {
        $('#all').removeClass('active').hide();
        $('#dont-read').removeClass('active').hide();
        $('#third').fadeIn().addClass('active').show();
    }

    addCladdActive() {
        $('#all').addClass('active');
    }

    getContactRequest = () => {
        this._messageService.getContactRequest()
            .subscribe(
            messages => {
                this.messages = messages;
                this.numberOfItems = this.messages.length;
            },
            error => this.errorString = <any>error
            );
    }

    getDontReadMessages = () => {
        this._messageService.getDontReadMessages()
            .subscribe(
            dontReadMessages => {
                this.dontReadMessages = dontReadMessages;
                this.numberOfItems = this.messages.length;
            },
            error => this.errorString = <any>error
            );
    }
}

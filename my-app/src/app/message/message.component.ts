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
    read: boolean = false;
    numberOfItems: number;
    numberOfAllItems: number;
    numberOfPages: number;
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

    readMessage(): void {
        if (this.read) {
            this.numberOfItems = this.dontReadMessages.length;
            this.numberOfPages = Math.ceil(this.numberOfItems / this.limit);
        } else {
            this.numberOfAllItems = this.messages.length;
            this.numberOfPages = Math.ceil(this.numberOfAllItems / this.limit);
        }
        if (this.page > this.numberOfPages) {
            this.page = this.numberOfPages;
        }
    }

    nextPage(): void {
        if (this.page < this.numberOfPages) {
            this.page += 1;
        }
    }

    previousPage(): void {
        if (this.page > 1) {
            this.page -= 1;
        }
    }

    all = (): void => {
        $('#dont-read').hide();
        $('#all').fadeIn().show();
        this.read = false;
        this.readMessage();
    }

    dontRead = (): void => {
        $('#all').hide();
        $('#dont-read').fadeIn().show();
        this.read = true;
        this.readMessage();
    }

    addCladdActive() {
        $('#all').addClass('active');
    }

    getContactRequest = () => {
        this._messageService.getContactRequest()
            .subscribe(
            messages => {
                this.messages = messages;
                this.numberOfAllItems = this.messages.length;
                this.numberOfPages = Math.ceil(this.numberOfAllItems / this.limit);
            },
            error => this.errorString = <any>error
            );
    }

    getDontReadMessages = () => {
        this._messageService.getDontReadMessages()
            .subscribe(
            dontReadMessages => {
                this.dontReadMessages = dontReadMessages;
                this.numberOfItems = this.dontReadMessages.length;
                this.numberOfPages = Math.ceil(this.numberOfItems / this.limit);
            },
            error => this.errorString = <any>error
            );
    }
}

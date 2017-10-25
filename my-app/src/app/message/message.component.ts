import { Component, Input, OnInit, OnChanges } from '@angular/core';
import { Http } from '@angular/http';
import { RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { MessageService } from './message.service';

@Component({
    selector: 'app-form',
    templateUrl: './message.component.html',
    styleUrls: ['./message.component.css'],
    providers: [MessageService]
})
export class MessageComponent implements OnInit {

    messages;
    errorString;

    constructor(private _messageService: MessageService) { }

    ngOnInit() {
        this.addCladdActive();
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
            messages => { this.messages = messages; },
            error => this.errorString = <any>error
            );
    }



}


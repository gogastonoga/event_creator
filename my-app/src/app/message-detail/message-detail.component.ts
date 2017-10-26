import 'rxjs/add/operator/switchMap';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Routes, Params } from '@angular/router';
import { Location } from '@angular/common';
import { Contact } from '../contact/contact';
import { MessageService } from '../message/message.service';

@Component({
    selector: 'item-detail',
    templateUrl: './message-detail.component.html',
    styleUrls: ['./message-detail.component.css'],
    providers: [MessageService]
})

export class MessageDetailComponent implements OnInit {
    id;
    private sub: any;
    message;
    sessionId;

    constructor(
        private _messageService: MessageService,
        private route: ActivatedRoute,
        private location: Location
    ) {

    }
    ngOnInit() {

        this.route.params.subscribe((params: Params) => {
            this.id = params['id'];
        });

        this.route.paramMap
            .switchMap((params: ParamMap) => this._messageService.getMessage(this.id))
            .subscribe(message => this.message = message);
        console.log(this.message);
    }



    goBack(): void {
        this.location.back();
    }
}
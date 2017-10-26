import 'rxjs/add/operator/switchMap';
import { Component, OnInit }        from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';
import { Contact }        from '../contact/contact';
import { MessageService } from '../message/message.service';

@Component({
  selector: 'item-detail',
  templateUrl: './message-detail.component.html',
  styleUrls: ['./message-detail.component.css'],
  providers: [MessageService]
})

export class MessageDetailComponent implements OnInit {
    id: number;
    private sub: any;
  message;

  constructor(
    private _messageService: MessageService,
    private route: ActivatedRoute,
    private location: Location
  ) {
      
  }
  ngOnInit(): void {
    this.route.params.subscribe(params => {
        const id = Number.parseInt(params['paramKey']);
     
 let a = params.toString();
 console.log(a);
        this.route.paramMap
        .switchMap((params: ParamMap) => this._messageService.getMessage(id))
        .subscribe(message => this.message = message);
     });
    
  }

  goBack(): void {
    this.location.back();
  }
}
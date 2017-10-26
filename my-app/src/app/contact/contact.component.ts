import { Component, OnInit, Input } from '@angular/core';
import {Contact} from './contact';
import {ContactService} from './contact.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css'],
  providers: [ContactService]
})
export class ContactComponent implements OnInit {

  @Input() contact: Contact;
  responseStatus: string;
  returnMsg: string;

  constructor(private _contactService: ContactService) {
  }

  ngOnInit(): void {
    this.contact = new Contact();
  }

  createContact() {
    this._contactService.create(this.contact).subscribe(
      data => console.log(this.responseStatus = data),
      err => console.log(err),
      () => this.returnMsg = 'Item is added!'
    );
  }
}
import { Component, OnInit } from '@angular/core';
import { ContentService } from '../content/content.service';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css'],
  providers: [ContentService]
})
export class StartComponent implements OnInit {

  content: Object;
  errorString: string;
  responseStatus: Object = [];
  returnMsg: String;

  constructor(private _contentService: ContentService) {

  }

  ngOnInit() {
    this.getContent();

  }

  getContent = () => {

    this._contentService.getContent()
      .subscribe(
      items => { this.content = items; console.log(this.content); },
      error => this.errorString = <any>error
      );
  }

  editHomepage(content: Object) {
    this._contentService.createEvent(content).subscribe(
      data => console.log(this.responseStatus = data),
      err => console.log(err),
      () => this.returnMsg = 'Event is created!'
    );
  }

}

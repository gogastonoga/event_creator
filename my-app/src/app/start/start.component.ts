import { Component, OnInit, Input, ChangeDetectorRef, NgZone, ChangeDetectionStrategy } from '@angular/core';
import { ContentService } from '../content/content.service';
import { UserService } from '../services/user.service';

export class HomePageDto {
  description: string;
  backgroundVideoUrl: string;
  backgroundImageUrl: string;
  contactRequestFormat: string;
}
@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css'],
  providers: [ContentService],
})
export class StartComponent implements OnInit {

  @Input() start: HomePageDto;
  content;
  errorString: string;
  responseStatus: Object = [];
  editStatus: boolean = false;
  disabledEdit: boolean = true;

  constructor(private _contentService: ContentService, private _userService: UserService) {
  }

  ngOnInit() {
    this.getContent();
    this.start = new HomePageDto();
    if (localStorage.getItem('DEdit') === 'false') {
      this.disabledEdit = false;
    }
  }

  getContent = () => {
    this._contentService.getContent()
      .subscribe(
      items => { this.content = items; console.log(this.content); },
      error => this.errorString = <any>error
      );
  }

  edit() {
    if (!this.disabledEdit) {
      if (this.editStatus) {
        $('#homeDescription').prop('readonly', true);
        $('#hint1').slideUp();
        $('#hint2').slideUp();
        this.editHomepage();
      } else {
        $('#homeDescription').prop('readonly', false);
        $('#hint1').slideDown();
        $('#hint2').slideDown();
      }
    }
  }

  editHomepage() {
    this._contentService.editContentHomePage(this.start).subscribe(
      data => console.log(this.responseStatus = data)
    );
  }
}
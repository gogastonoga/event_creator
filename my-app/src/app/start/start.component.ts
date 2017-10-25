import { Component, OnInit, Input, Directive } from '@angular/core';
import { ContentService } from '../content/content.service';
import { UserService } from '../services/user.service';
import { FileSelectDirective, FileDropDirective, FileUploader } from 'ng2-file-upload/ng2-file-upload'

export class HomePageDto {
  description: string;
  backgroundVideoUrl: string;
  backgroundImageUrl: string;
  contactRequestFormat: string;
}

export class FileHolder {
  public serverResponse: any;
  public pending: boolean = false;
  constructor(public src: string, public file: File) { }
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
  message;

  constructor(private _contentService: ContentService, private _userService: UserService) {
  }

  ngOnInit() {
    $('#message').hide();
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

  
  myHeaders: { [name: string]: any } = {
    'Authorization': 'Bearer' + localStorage.getItem('access_token')
};

onUploadFinished(file: FileHolder) {
  console.log(JSON.stringify(file.serverResponse));
  $('#message').show();
}

onRemoved(file: FileHolder) {
  // do some stuff with the removed file.
}

onUploadStateChanged(state: boolean) {
  console.log(JSON.stringify(state));
}

URL = 'http://localhost:8080/wolimierz/media/video';

    public uploader:FileUploader = new FileUploader({
      url: this.URL,
      authToken: 'Bearer ' + localStorage.getItem('access_token'),
      disableMultipart: true
    });
    public hasBaseDropZoneOver:boolean = false;
    public hasAnotherDropZoneOver:boolean = false;
   
    public fileOverBase(e:any):void {
      this.hasBaseDropZoneOver = e;
    }
   
    public fileOverAnother(e:any):void {
      this.hasAnotherDropZoneOver = e;
    }

}
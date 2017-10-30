import { Component, OnInit, Input, Directive, ViewChild } from '@angular/core';
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
  @ViewChild('videoPlayer') videoplayer: any;
  URL = 'http://localhost:8080/wolimierz/media/video';
  newUrl: string;

  constructor(private _contentService: ContentService, private _userService: UserService) {
  }

  ngOnInit() {
    $('#message').hide();
    $('#message2').hide();
    this.getContent();
    this.start = new HomePageDto();
    if (localStorage.getItem('DEdit') === 'false') {
      this.disabledEdit = false;
    }
  }

  @ViewChild('selectedFile') selectedFile: any;
  clearSelectedFile(): void {
    if (this.uploader.queue.length > 0) {
      this.selectedFile.nativeElement.value = '';
      this.uploader.clearQueue();
    }
  }

  itemUpload(): void {
    $('#message2').delay(1000).fadeIn();
    this.uploader.queue[0].upload();
    $('#addVideo').prop('disabled', true);
  }

  getContent = () => {
    this._contentService.getContent()
      .subscribe(
      items => { this.content = items; this.createVideoUrl(this.content.mainPage.backgroundVideoUrl) },
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
    $('#message').fadeIn();
  }

  public uploader: FileUploader = new FileUploader({
    url: this.URL,
    authToken: 'Bearer ' + localStorage.getItem('access_token'),
    itemAlias: 'video'
  });
  public hasBaseDropZoneOver: boolean = false;
  public hasAnotherDropZoneOver: boolean = false;

  public fileOverBase(e: any): void {
    this.hasBaseDropZoneOver = e;
  }

  public fileOverAnother(e: any): void {
    this.hasAnotherDropZoneOver = e;
  }

  toggleVideo(event: any) {
    this.videoplayer.nativeElement.play();
  }

  createVideoUrl(url) {
    this.newUrl = 'http://' + url;
  }

  customStyle = {
    layout: {
      "width": "370px",
      "border": "none",
      "background-color": "#F5F5F5"
    },
    clearButton: {
      "display": "none"
    },
    selectButton: {
      "background-color": "rgba(0, 162, 96, 0.70",
      "color": "#fff",
      "height": "35px",
      "font-size": "12px",
      "padding-top": "-30px",
      "margin-left": "53px",
      "margin-top": "-10px"
    },
    previewPanel: {
      "background-color": "#F5F5F5",
      "padding-left": "65px"
    }
  }

}
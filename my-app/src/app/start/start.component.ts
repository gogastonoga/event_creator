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
      this.uploader.queue[0].remove();
    }
  }

  clearQueue(): void {
    if (this.uploader.queue.length > 0) {
      this.uploader.clearQueue();
    }
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
    console.log(JSON.stringify(file.serverResponse));
    $('#message').show();
  }

  onRemoved(file: FileHolder) {
    // do some stuff with the removed file.
  }

  onUploadStateChanged(state: boolean) {
    console.log(JSON.stringify(state));
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
    //console.log(url);
    console.log(url);
    console.log(this.newUrl);
    this.newUrl = 'http://' + url;
    console.log(this.newUrl);
    //return 'http://' + url;
    }

}
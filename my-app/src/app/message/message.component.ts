import { Component, Input, OnInit, OnChanges } from '@angular/core';
import { Http } from '@angular/http';
import { RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';


@Component({
    selector: 'app-form',
    templateUrl: './message.component.html',
    styleUrls: ['./message.component.css'],
})
export class MessageComponent implements OnInit {




    constructor(){}
    
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
        // const cur = $('.form-panel').index($('.form-panel.active'));
        // if (cur !== 0) {
        //     $('.form-panel').removeClass('active').hide();
        //     $('.form-panel').eq(cur - 1).fadeIn().addClass('active');
        // }
    }

    addCladdActive() {
        $('#all').addClass('active');
    }

}


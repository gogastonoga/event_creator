import { Component, OnInit, Input } from '@angular/core';
import { CreateUserService } from './create_user.service';
import { User } from './user';
import { ReactiveFormsModule, FormsModule, FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import {
    MatSnackBar, MatSnackBarConfig,
    MatSnackBarHorizontalPosition,
    MatSnackBarVerticalPosition
} from '@angular/material';
import { Dir } from '@angular/cdk/bidi';
import { ViewEncapsulation } from '@angular/core';
import { AppDataService } from '../services/app-data.service';

@Component({
    selector: 'create-user',
    templateUrl: './create_user.component.html',
    styleUrls: ['./create_user.component.css'],
    providers: [CreateUserService, Dir],
    encapsulation: ViewEncapsulation.None,
    preserveWhitespaces: false,
})
export class CreateUserComponent implements OnInit {

    errorString: string;
    responseStatus: Object = [];
    returnMsg: String;
    @Input() user: User;
    userForm: FormGroup;
    role = ['ADMIN', 'STAFF'];

    message: string = 'Snack Bar opened.';
    actionButtonLabel: string = 'Zamknij';
    action: boolean = true;
    setAutoHide: boolean = true;
    autoHide: number = 5000;
    addExtraClass: boolean = true;
    horizontalPosition: MatSnackBarHorizontalPosition = 'center';
    verticalPosition: MatSnackBarVerticalPosition = 'bottom';

    constructor(private _createUserService: CreateUserService, private fb: FormBuilder, public snackBar: MatSnackBar, private dir: Dir) {
    }

    ngOnInit() {
        this.user = new User();
        this.createForm();
    }

    createUser() {
        const formModel = this.userForm.value;
        this._createUserService.create(formModel).subscribe(
            data => console.log(this.responseStatus = data),
            err => { this.message = 'Wystąpił problem podczas dodania użytkownika.' },
            () => this.message = 'Użytkownik został dodany.'
        );
    }

    createForm() {
        this.userForm = this.fb.group({
            name: ['', Validators.required],
            surname: ['', Validators.required],
            password: new FormControl(this.user.password, [
                Validators.required,
                Validators.minLength(6)]),
            email: ['', Validators.required],
            role: ['', Validators.required]
        });
    }

    open() {
        let config = new MatSnackBarConfig();
        config.verticalPosition = this.verticalPosition;
        config.horizontalPosition = this.horizontalPosition;
        config.duration = this.setAutoHide ? this.autoHide : 0;
        config.extraClasses = this.addExtraClass ? ['party'] : undefined;
        config.direction = this.dir.value;
        this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
    }
}
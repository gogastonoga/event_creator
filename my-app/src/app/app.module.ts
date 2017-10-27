import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Directive } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import {
  MatNativeDateModule, MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule,
  MatCheckboxModule, MatChipsModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatFormFieldModule,
  MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatPaginatorModule, MatProgressBarModule,
  MatProgressSpinnerModule, MatRadioModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule,
  MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule, MatStepperModule
} from '@angular/material';
import { Http, HttpModule } from '@angular/http';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { StartComponent } from './start/start.component';
import { FormComponent } from './form/form.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { CreateUserComponent } from './create_user/create_user.component';
import { CreateUserService } from './create_user/create_user.service';
import { CostComponent } from './cost/cost.component';
import { Dir } from '@angular/cdk/bidi';
import { ContactComponent } from './contact/contact.component';
import { MessageComponent } from './message/message.component';
import { UserService } from './services/user.service';
import { CostService } from './cost/cost.service';
import { AuthenticationService } from './services/authentication.service';
import { AuthGuard } from './guards/auth-guard.service';
import { AdminAuthGuard } from './guards/admin-auth-guard.service';
import { TOKEN_NAME } from './services/auth.constant';
import { AppDataService } from './services/app-data.service';
import { AuthConfig, AuthHttp } from 'angular2-jwt';
import { ImageUploadModule } from "angular2-image-upload";
import { InputMaskModule } from 'ng2-inputmask';
import { FileUploadModule } from 'ng2-file-upload/file-upload/file-upload.module';
import { FileSelectDirective, FileDropDirective } from 'ng2-file-upload';
import { MessageService } from './message/message.service';
import { ContactService } from './contact/contact.service';
import { MessageDetailComponent } from './message-detail/message-detail.component';
import * as $ from 'jquery';

export function authHttpServiceFactory(http: Http) {
  return new AuthHttp(new AuthConfig({
    headerPrefix: 'Bearer',
    tokenName: TOKEN_NAME,
    globalHeaders: [{ 'Content-Type': 'application/json' }],
    noJwtError: false,
    noTokenScheme: true,
    tokenGetter: (() => localStorage.getItem(TOKEN_NAME))
  }), http);
}

@Directive({ selector: '[ng2FileSelect]' })
@Directive({ selector: '[ng2FileDrop]' })

@NgModule({
  declarations: [
    AppComponent, StartComponent, FormComponent, LoginComponent, AdminComponent, CostComponent,
    UserComponent, CreateUserComponent, ContactComponent, MessageComponent, MessageDetailComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, FormsModule, MatAutocompleteModule, MatNativeDateModule, BrowserAnimationsModule, RouterModule,
    MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatDatepickerModule, MatDialogModule,
    MatExpansionModule, MatFormFieldModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatPaginatorModule,
    MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule,
    MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule, MatStepperModule, HttpModule,
    ReactiveFormsModule, ImageUploadModule.forRoot(), InputMaskModule, FileUploadModule
  ],
  providers: [
    { provide: AuthHttp, useFactory: authHttpServiceFactory, deps: [Http] },
    AuthenticationService, UserService, AuthGuard, AdminAuthGuard, AppDataService, CostService, CreateUserService, MessageService, ContactService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
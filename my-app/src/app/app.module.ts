import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { MatNativeDateModule, MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule,
  MatCheckboxModule, MatChipsModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatFormFieldModule,
  MatGridListModule, MatIconModule, MatInputModule, MatListModule,  MatMenuModule,  MatPaginatorModule, MatProgressBarModule,
  MatProgressSpinnerModule,  MatRadioModule,  MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, 
  MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule, MatStepperModule} from '@angular/material';
import { Http, HttpModule } from '@angular/http';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { StartComponent } from './start/start.component';
import { FormComponent } from './form/form.component';
import { RouterModule } from '@angular/router';
import { EditDialogComponent } from './dialog/edit-dialog/edit-dialog.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { CreateUserComponent } from './create_user/create_user.component';
import { CreateUserService } from './create_user/create_user.service';
import { ContactComponent } from './contact/contact.component';

import { UserService } from './services/user.service';
import { CostService } from './cost/cost.service';
import { AuthenticationService } from './services/authentication.service';
import { AuthGuard } from './guards/auth-guard.service';
import { AdminAuthGuard } from './guards/admin-auth-guard.service';
import { TOKEN_NAME } from './services/auth.constant';
import { AppDataService } from './services/app-data.service';
import { AuthConfig, AuthHttp } from 'angular2-jwt';

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

@NgModule({
  declarations: [
    AppComponent, StartComponent, FormComponent, EditDialogComponent, LoginComponent, AdminComponent,
    UserComponent, CreateUserComponent, ContactComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, FormsModule, MatAutocompleteModule, MatNativeDateModule, BrowserAnimationsModule, RouterModule,
    MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatDatepickerModule, MatDialogModule, 
    MatExpansionModule, MatFormFieldModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatPaginatorModule, 
    MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, 
    MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule, MatStepperModule, HttpModule,
    ReactiveFormsModule
  ],
  providers: [
    { provide: AuthHttp, useFactory: authHttpServiceFactory, deps: [Http] },
    AuthenticationService,
    UserService,
    AuthGuard,
    AdminAuthGuard,
    AppDataService,
    CostService,
    CreateUserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
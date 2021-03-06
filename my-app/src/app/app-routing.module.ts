import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { StartComponent } from './start/start.component';
import { FormComponent } from './form/form.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { AdminAuthGuard } from './guards/admin-auth-guard.service';
import { AuthGuard } from './guards/auth-guard.service';
import { UserComponent } from './user/user.component';
import { CostComponent } from './cost/cost.component';
import { MessageComponent } from './message/message.component';
import { ContactComponent } from './contact/contact.component';
import { CreateUserComponent } from './create_user/create_user.component';
import { MessageDetailComponent } from './message-detail/message-detail.component';

const routes: Routes = [
  { 
    path: '', redirectTo: '/start', pathMatch: 'full' 
  },
  { 
    path: 'start', 
    component: StartComponent 
  },
  { 
    path: 'form', 
    component: FormComponent 
  },
  { 
    path: 'login', 
    component: LoginComponent 
  },
  { 
    path: 'contact', 
    component: ContactComponent 
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard, AdminAuthGuard]
  },
  {
    path: 'user',
    component: UserComponent,
    canActivate: [AuthGuard]
  },
  { 
    path: 'create-user', 
    component: CreateUserComponent, 
    canActivate: [AuthGuard, AdminAuthGuard] 
  },
  { 
    path: 'cost-settings', 
    component: CostComponent, 
    canActivate: [AuthGuard, AdminAuthGuard] 
  },
  { 
    path: 'messages', 
    component: MessageComponent, 
    canActivate: [AuthGuard, AdminAuthGuard] 
  },
  { 
    path: 'detail/:id' , 
    component: MessageDetailComponent,
    canActivate: [AuthGuard, AdminAuthGuard] 
   },
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})

export class AppRoutingModule {
}
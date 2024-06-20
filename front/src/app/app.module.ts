import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { PostComponent } from './pages/post/post.component';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { TopicComponent } from './pages/topic/topic.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { UserComponent } from './pages/user/user.component';
import { CreatePostComponent } from './pages/create-post/create-post.component';
import { MatSelectModule } from '@angular/material/select';
import { PostDetailComponent } from './pages/post-detail/post-detail.component';
import { LoginNavbarComponent } from './pages/shared/login-navbar/login-navbar.component';
import { BackArrowLinkComponent } from './pages/shared/back-arrow-link/back-arrow-link.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { NavbarHeaderComponent } from './pages/shared/navbar-header/navbar-header.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { CommonNavbarComponent } from './pages/shared/common-navbar/common-navbar.component';
import { MobileNavbarComponent } from './pages/shared/mobile-navbar/mobile-navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    PostComponent,
    TopicComponent,
    UserComponent,
    CreatePostComponent,
    PostDetailComponent,
    LoginNavbarComponent,
    BackArrowLinkComponent,
    NavbarHeaderComponent,
    CommonNavbarComponent,
    MobileNavbarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatCardModule,
    MatIconModule,
    MatSnackBarModule,
    MatToolbarModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatFormFieldModule,
    MatSelectModule,
    FontAwesomeModule,
    MatSidenavModule,
    MatListModule,
    MatGridListModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}

import { Component, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SessionInformation } from '../../models/session-information';
import { SessionService } from '../../services/session.service';
import { LoginRequest } from '../../models/login-request';
import { AuthService } from '../../services/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnDestroy {
  hide = true;
  onError = false;
  linkToBack = '/';

  loginService$: Subscription | undefined;

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) {}

  form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.min(8)]],
  });

  public submit(): void {
    let loginRequest = this.form.value as LoginRequest;
    this.loginService$ = this.authService.login(loginRequest).subscribe({
      next: (response: SessionInformation) => {
        this.sessionService.logIn(response);
        this.router.navigate(['/post']);
      },
      error: () => (this.onError = true),
    });
  }

  public ngOnDestroy(): void {
    if (this.loginService$) {
      this.loginService$.unsubscribe();
    }
  }
}

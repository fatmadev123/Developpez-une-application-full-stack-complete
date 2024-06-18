import { Component, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { RegisterRequest } from '../../models/register-request';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnDestroy {
  onError = false;
  linkToBack = '/';
  registerService$: Subscription | undefined;

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    userName: [
      '',
      [Validators.required, Validators.min(3), Validators.max(20)],
    ],
    password: [
      '',
      [
        Validators.required,
        Validators.pattern(
          new RegExp(
            '((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%?=*&]).{8,50})'
          )
        ),
      ],
    ],
  });

  public submit(): void {
    let registerRequest = this.form.value as RegisterRequest;
    this.registerService$ = this.authService
      .register(registerRequest)
      .subscribe({
        next: (_: void) => this.router.navigate(['/login']),
        error: (_) => (this.onError = true),
      });
  }

  public ngOnDestroy(): void {
    if (this.registerService$) {
      this.registerService$.unsubscribe();
    }
  }
}

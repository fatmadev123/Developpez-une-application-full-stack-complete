import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class UnauthGuard implements CanActivate {
  constructor(private router: Router) {}

  public canActivate(): boolean {
    if (localStorage.getItem('token')) {
      this.router.navigateByUrl('/post');
      return false;
    }
    return true;
  }
}

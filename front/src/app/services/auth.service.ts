import { SessionInformation } from './../models/session-information';
import { LoginRequest } from './../models/login-request';
import { RegisterRequest } from './../models/register-request';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private pathService = 'http://localhost:8080/api/auth';

  constructor(private httpClient: HttpClient) {}

  public register(registerRequest: RegisterRequest): Observable<void> {
    return this.httpClient.post<void>(
      `${this.pathService}/register`,
      registerRequest
    );
  }

  public login(loginRequest: LoginRequest): Observable<SessionInformation> {
    return this.httpClient.post<SessionInformation>(
      `${this.pathService}/login`,
      loginRequest
    );
  }
}

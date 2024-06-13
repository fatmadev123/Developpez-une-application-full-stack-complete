import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { RegisterRequest } from '../models/register-request';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private pathService = 'http://localhost:8080/api/user';

  constructor(private httpClient: HttpClient) {}

  public getMe(): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}`);
  }

  public update(
    id: string,
    registerRequest: RegisterRequest
  ): Observable<void> {
    return this.httpClient.put<void>(
      `${this.pathService}/${id}`,
      registerRequest
    );
  }

  public updates(registerRequest: RegisterRequest): Observable<User> {
    return this.httpClient.put<User>(`${this.pathService}`, registerRequest);
  }
}

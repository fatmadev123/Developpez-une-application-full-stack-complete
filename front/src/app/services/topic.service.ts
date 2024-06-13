import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Topic } from '../models/topic';

@Injectable({
  providedIn: 'root',
})
export class TopicService {
  private pathService = 'http://localhost:8080/api/topic';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(this.pathService);
  }

  public getFollowedTopics(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.pathService}/user`);
  }

  public follow(id: string): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/${id}/follow`, null);
  }

  public unfollow(id: string): Observable<void> {
    return this.httpClient.post<void>(
      `${this.pathService}/${id}/unfollow`,
      null
    );
  }
}

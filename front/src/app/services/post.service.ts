import { PostRequest } from './../models/post-request';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  private pathService = 'http://localhost:8080/api/post';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.pathService);
  }

  public getById(id: string): Observable<Post> {
    return this.httpClient.get<Post>(`${this.pathService}/${id}`);
  }

  public create(postRequest: PostRequest): Observable<Post> {
    return this.httpClient.post<Post>(`${this.pathService}`, {
      ...postRequest,
    });
  }
}

import { CommentRequest } from './../models/comment-request';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  private pathService = 'http://localhost:8080/api/post';

  constructor(private httpClient: HttpClient) {}

  public getAllByPostId(postId: string): Observable<Comment[]> {
    return this.httpClient.get<Comment[]>(
      `${this.pathService}/${postId}/comment`
    );
  }

  public create(
    postId: string,
    createCommentRequest: CommentRequest
  ): Observable<Comment> {
    return this.httpClient.post<Comment>(
      `${this.pathService}/${postId}/comment`,
      { ...createCommentRequest }
    );
  }
}

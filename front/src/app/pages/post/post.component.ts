import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Post } from 'src/app/models/post';
import { PostService } from 'src/app/services/post.service';
@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss'],
})
export class PostComponent implements OnInit {
  posts: Post[] | undefined;
  isDescending = true;
  getAllService$: Subscription | undefined;

  constructor(private postService: PostService) {}

  ngOnInit(): void {
    this.getAll();
  }

  public getAll(): void {
    this.getAllService$ = this.postService.getAll().subscribe((posts) => {
      this.posts = posts;
      this.sortByDescending();
    });
  }

  public sortByDescending(): void {
    this.posts?.sort(
      (a, b) => new Date(b.date).getTime() - new Date(a.date).getTime()
    );
    this.isDescending = true;
  }

  public sortByAscending(): void {
    this.posts?.sort(
      (b, a) => new Date(b.date).getTime() - new Date(a.date).getTime()
    );
    this.isDescending = false;
  }

  public ngOnDestroy(): void {
    if (this.getAllService$) {
      this.getAllService$.unsubscribe();
    }
  }
}

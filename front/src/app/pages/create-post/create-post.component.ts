import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { PostRequest } from 'src/app/models/post-request';
import { Topic } from 'src/app/models/topic';
import { PostService } from 'src/app/services/post.service';
import { TopicService } from 'src/app/services/topic.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss'],
})
export class CreatePostComponent implements OnInit, OnDestroy {
  topics: Topic[] | undefined;
  linkToBack = '/post';
  createService$: Subscription | undefined;

  constructor(
    private router: Router,
    private postService: PostService,
    private topicService: TopicService,
    private fb: FormBuilder
  ) {}

  form = this.fb.group({
    topicId: ['', [Validators.required]],
    title: ['', [Validators.required, Validators.max(50)]],
    content: ['', [Validators.required, Validators.max(5000)]],
  });

  ngOnInit(): void {
    // to gather all the topics into the topic dropdown
    this.topicService
      .getAll()
      .subscribe((topics: Topic[]) => (this.topics = topics));
  }

  public submit() {
    let createPostRequest = this.form.value as PostRequest;
    this.createService$ = this.postService
      .create(createPostRequest)
      .subscribe(() => {
        this.router.navigateByUrl('/post');
      });
  }

  public ngOnDestroy(): void {
    if (this.createService$) {
      this.createService$.unsubscribe();
    }
  }
}

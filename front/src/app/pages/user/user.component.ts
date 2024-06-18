import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { RegisterRequest } from 'src/app/models/register-request';
import { Topic } from 'src/app/models/topic';
import { User } from 'src/app/models/user';
import { SessionService } from 'src/app/services/session.service';
import { TopicService } from 'src/app/services/topic.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserComponent implements OnInit {
  user: User | undefined;
  topics: Topic[] | undefined;
  onError = false;
  form!: FormGroup;
  getMeService$: Subscription | undefined;
  getTopicsService$: Subscription | undefined;
  unfollowService$: Subscription | undefined;
  updateUserService$: Subscription | undefined;

  constructor(
    private router: Router,
    private userService: UserService,
    private topicService: TopicService,
    private sessionService: SessionService,
    private fb: FormBuilder
  ) {}

  public ngOnInit(): void {
    this.getMeService$ = this.userService
      .getMe()
      .subscribe((user: User) => (this.user = user));

    this.form = this.fb.group({
      email: [[Validators.required]],
      name: [[Validators.required]],
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

    this.refreshFollowedTopics();
  }

  public refreshFollowedTopics(): void {
    this.getTopicsService$ = this.topicService
      .getFollowedTopics()
      .subscribe((topics: Topic[]) => (this.topics = topics));
  }

  public unfollow(id: number) {
    this.unfollowService$ = this.topicService
      .unfollow(id.toString())
      .subscribe(() => {
        this.refreshFollowedTopics();
      });
  }

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    if (this.user) {
      this.updateUserService$ = this.userService
        .updates(registerRequest)
        .subscribe({
          next: () => {
            this.sessionService.logOut();
            this.router.navigate(['/login']);
          },
          error: (_) => (this.onError = true),
        });
    }
  }

  public logOut() {
    this.sessionService.logOut();
    this.router.navigateByUrl(`/`);
  }

  public ngOnDestroy(): void {
    if (this.getMeService$) {
      this.getMeService$.unsubscribe();
    }
    if (this.getTopicsService$) {
      this.getTopicsService$.unsubscribe();
    }
    if (this.updateUserService$) {
      this.updateUserService$.unsubscribe();
    }
    if (this.unfollowService$) {
      this.unfollowService$.unsubscribe();
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Topic } from 'src/app/models/topic';
import { User } from 'src/app/models/user';
import { TopicService } from 'src/app/services/topic.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.scss'],
})
export class TopicComponent implements OnInit {
  topics: Topic[] | undefined;
  user: User | undefined;
  getUserService$: Subscription | undefined;
  getAllService$: Subscription | undefined;
  followService$: Subscription | undefined;

  constructor(
    private topicService: TopicService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.getUser();
    this.getAll();
  }

  public getUser() {
    this.getUserService$ = this.userService
      .getMe()
      .subscribe((user: User) => (this.user = user));
  }

  public getAll(): void {
    this.getAllService$ = this.topicService.getAll().subscribe((topics) => {
      this.topics = topics;
    });
  }

  public follow(id: number) {
    this.followService$ = this.topicService
      .follow(id.toString())
      .subscribe(() => {
        this.getUser();
        this.getAll();
      });
  }

  // The result will set the button to follow the topic (disabled or not)
  public isAlreadyFollowed(topicId: number): boolean {
    if (this.user) {
      return this.user?.topicIds.includes(topicId);
    }
    return false;
  }

  public ngOnDestroy(): void {
    if (this.getUserService$) {
      this.getUserService$.unsubscribe();
    }
    if (this.getAllService$) {
      this.getAllService$.unsubscribe();
    }
    if (this.followService$) {
      this.followService$.unsubscribe();
    }
  }
}

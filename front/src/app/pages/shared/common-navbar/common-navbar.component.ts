import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUser } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-common-navbar',
  templateUrl: './common-navbar.component.html',
  styleUrls: ['./common-navbar.component.scss'],
})
export class CommonNavbarComponent implements OnInit {
  onPosts: boolean = false;
  onTopics: boolean = false;
  onProfile: boolean = false;

  faUserIcon = faUser;

  constructor(private router: Router) {}

  ngOnInit(): void {
    if (this.router.url.includes('post')) {
      this.onPosts = true;
      this.onTopics = false;
      this.onProfile = false;
    }
    if (this.router.url.includes('topic')) {
      this.onPosts = false;
      this.onTopics = true;
      this.onProfile = false;
    }
    if (this.router.url.includes('user')) {
      this.onPosts = false;
      this.onTopics = false;
      this.onProfile = true;
    }
  }
}

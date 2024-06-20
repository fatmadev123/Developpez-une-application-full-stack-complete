import { Component, OnInit } from '@angular/core';
import { faUser, faWindowClose } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-mobile-navbar',
  templateUrl: './mobile-navbar.component.html',
  styleUrls: ['./mobile-navbar.component.scss'],
})
export class MobileNavbarComponent implements OnInit {
  faUserIcon = faUser;
  faWindowCloseIcon = faWindowClose;

  constructor() {}

  ngOnInit(): void {}
}

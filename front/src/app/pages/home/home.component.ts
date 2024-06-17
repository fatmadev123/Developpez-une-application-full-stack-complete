import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  constructor(private sessionService: SessionService, private router: Router) {}

  ngOnInit(): void {}

  public goToRegister(): void {
    this.sessionService.logOut();
    this.router.navigate(['/register']);
  }
}

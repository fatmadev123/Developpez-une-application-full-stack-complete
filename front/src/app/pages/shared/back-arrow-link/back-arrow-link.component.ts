import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-back-arrow-link',
  templateUrl: './back-arrow-link.component.html',
  styleUrls: ['./back-arrow-link.component.scss'],
})
export class BackArrowLinkComponent implements OnInit {
  @Input() linkToBack: string = '';

  constructor() {}

  ngOnInit(): void {}
}

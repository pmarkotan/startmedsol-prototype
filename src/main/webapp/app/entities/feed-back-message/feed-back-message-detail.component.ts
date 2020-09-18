import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFeedBackMessage } from 'app/shared/model/feed-back-message.model';

@Component({
  selector: 'jhi-feed-back-message-detail',
  templateUrl: './feed-back-message-detail.component.html',
})
export class FeedBackMessageDetailComponent implements OnInit {
  feedBackMessage: IFeedBackMessage | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ feedBackMessage }) => (this.feedBackMessage = feedBackMessage));
  }

  previousState(): void {
    window.history.back();
  }
}

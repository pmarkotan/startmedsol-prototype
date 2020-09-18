import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFeedBackMessage } from 'app/shared/model/feed-back-message.model';
import { FeedBackMessageService } from './feed-back-message.service';

@Component({
  templateUrl: './feed-back-message-delete-dialog.component.html',
})
export class FeedBackMessageDeleteDialogComponent {
  feedBackMessage?: IFeedBackMessage;

  constructor(
    protected feedBackMessageService: FeedBackMessageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.feedBackMessageService.delete(id).subscribe(() => {
      this.eventManager.broadcast('feedBackMessageListModification');
      this.activeModal.close();
    });
  }
}

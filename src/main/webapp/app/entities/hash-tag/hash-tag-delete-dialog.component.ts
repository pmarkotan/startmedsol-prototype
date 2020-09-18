import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHashTag } from 'app/shared/model/hash-tag.model';
import { HashTagService } from './hash-tag.service';

@Component({
  templateUrl: './hash-tag-delete-dialog.component.html',
})
export class HashTagDeleteDialogComponent {
  hashTag?: IHashTag;

  constructor(protected hashTagService: HashTagService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.hashTagService.delete(id).subscribe(() => {
      this.eventManager.broadcast('hashTagListModification');
      this.activeModal.close();
    });
  }
}

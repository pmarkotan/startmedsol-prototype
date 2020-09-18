import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDictionaryItem } from 'app/shared/model/dictionary-item.model';
import { DictionaryItemService } from './dictionary-item.service';

@Component({
  templateUrl: './dictionary-item-delete-dialog.component.html',
})
export class DictionaryItemDeleteDialogComponent {
  dictionaryItem?: IDictionaryItem;

  constructor(
    protected dictionaryItemService: DictionaryItemService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dictionaryItemService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dictionaryItemListModification');
      this.activeModal.close();
    });
  }
}

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDictionaryTranslation } from 'app/shared/model/dictionary-translation.model';
import { DictionaryTranslationService } from './dictionary-translation.service';

@Component({
  templateUrl: './dictionary-translation-delete-dialog.component.html',
})
export class DictionaryTranslationDeleteDialogComponent {
  dictionaryTranslation?: IDictionaryTranslation;

  constructor(
    protected dictionaryTranslationService: DictionaryTranslationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dictionaryTranslationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dictionaryTranslationListModification');
      this.activeModal.close();
    });
  }
}

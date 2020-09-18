import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEhrDocument } from 'app/shared/model/ehr-document.model';
import { EhrDocumentService } from './ehr-document.service';

@Component({
  templateUrl: './ehr-document-delete-dialog.component.html',
})
export class EhrDocumentDeleteDialogComponent {
  ehrDocument?: IEhrDocument;

  constructor(
    protected ehrDocumentService: EhrDocumentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ehrDocumentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ehrDocumentListModification');
      this.activeModal.close();
    });
  }
}

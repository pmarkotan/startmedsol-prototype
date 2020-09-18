import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExternalDocument } from 'app/shared/model/external-document.model';
import { ExternalDocumentService } from './external-document.service';

@Component({
  templateUrl: './external-document-delete-dialog.component.html',
})
export class ExternalDocumentDeleteDialogComponent {
  externalDocument?: IExternalDocument;

  constructor(
    protected externalDocumentService: ExternalDocumentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.externalDocumentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('externalDocumentListModification');
      this.activeModal.close();
    });
  }
}

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICaseTextDocumentation } from 'app/shared/model/case-text-documentation.model';
import { CaseTextDocumentationService } from './case-text-documentation.service';

@Component({
  templateUrl: './case-text-documentation-delete-dialog.component.html',
})
export class CaseTextDocumentationDeleteDialogComponent {
  caseTextDocumentation?: ICaseTextDocumentation;

  constructor(
    protected caseTextDocumentationService: CaseTextDocumentationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.caseTextDocumentationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('caseTextDocumentationListModification');
      this.activeModal.close();
    });
  }
}

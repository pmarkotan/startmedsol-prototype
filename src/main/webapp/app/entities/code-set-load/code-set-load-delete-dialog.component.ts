import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICodeSetLoad } from 'app/shared/model/code-set-load.model';
import { CodeSetLoadService } from './code-set-load.service';

@Component({
  templateUrl: './code-set-load-delete-dialog.component.html',
})
export class CodeSetLoadDeleteDialogComponent {
  codeSetLoad?: ICodeSetLoad;

  constructor(
    protected codeSetLoadService: CodeSetLoadService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.codeSetLoadService.delete(id).subscribe(() => {
      this.eventManager.broadcast('codeSetLoadListModification');
      this.activeModal.close();
    });
  }
}

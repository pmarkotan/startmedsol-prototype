import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICsProcedure } from 'app/shared/model/cs-procedure.model';
import { CsProcedureService } from './cs-procedure.service';

@Component({
  templateUrl: './cs-procedure-delete-dialog.component.html',
})
export class CsProcedureDeleteDialogComponent {
  csProcedure?: ICsProcedure;

  constructor(
    protected csProcedureService: CsProcedureService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.csProcedureService.delete(id).subscribe(() => {
      this.eventManager.broadcast('csProcedureListModification');
      this.activeModal.close();
    });
  }
}

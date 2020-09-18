import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPerformedProcedure } from 'app/shared/model/performed-procedure.model';
import { PerformedProcedureService } from './performed-procedure.service';

@Component({
  templateUrl: './performed-procedure-delete-dialog.component.html',
})
export class PerformedProcedureDeleteDialogComponent {
  performedProcedure?: IPerformedProcedure;

  constructor(
    protected performedProcedureService: PerformedProcedureService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.performedProcedureService.delete(id).subscribe(() => {
      this.eventManager.broadcast('performedProcedureListModification');
      this.activeModal.close();
    });
  }
}

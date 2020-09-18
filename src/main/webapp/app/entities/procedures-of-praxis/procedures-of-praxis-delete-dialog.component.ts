import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProceduresOfPraxis } from 'app/shared/model/procedures-of-praxis.model';
import { ProceduresOfPraxisService } from './procedures-of-praxis.service';

@Component({
  templateUrl: './procedures-of-praxis-delete-dialog.component.html',
})
export class ProceduresOfPraxisDeleteDialogComponent {
  proceduresOfPraxis?: IProceduresOfPraxis;

  constructor(
    protected proceduresOfPraxisService: ProceduresOfPraxisService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.proceduresOfPraxisService.delete(id).subscribe(() => {
      this.eventManager.broadcast('proceduresOfPraxisListModification');
      this.activeModal.close();
    });
  }
}

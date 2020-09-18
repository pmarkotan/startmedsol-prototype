import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPraxis } from 'app/shared/model/praxis.model';
import { PraxisService } from './praxis.service';

@Component({
  templateUrl: './praxis-delete-dialog.component.html',
})
export class PraxisDeleteDialogComponent {
  praxis?: IPraxis;

  constructor(protected praxisService: PraxisService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.praxisService.delete(id).subscribe(() => {
      this.eventManager.broadcast('praxisListModification');
      this.activeModal.close();
    });
  }
}

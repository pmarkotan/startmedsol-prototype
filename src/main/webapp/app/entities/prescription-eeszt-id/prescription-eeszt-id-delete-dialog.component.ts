import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrescriptionEesztId } from 'app/shared/model/prescription-eeszt-id.model';
import { PrescriptionEesztIdService } from './prescription-eeszt-id.service';

@Component({
  templateUrl: './prescription-eeszt-id-delete-dialog.component.html',
})
export class PrescriptionEesztIdDeleteDialogComponent {
  prescriptionEesztId?: IPrescriptionEesztId;

  constructor(
    protected prescriptionEesztIdService: PrescriptionEesztIdService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.prescriptionEesztIdService.delete(id).subscribe(() => {
      this.eventManager.broadcast('prescriptionEesztIdListModification');
      this.activeModal.close();
    });
  }
}

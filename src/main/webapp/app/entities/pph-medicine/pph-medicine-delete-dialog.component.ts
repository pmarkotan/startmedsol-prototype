import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPphMedicine } from 'app/shared/model/pph-medicine.model';
import { PphMedicineService } from './pph-medicine.service';

@Component({
  templateUrl: './pph-medicine-delete-dialog.component.html',
})
export class PphMedicineDeleteDialogComponent {
  pphMedicine?: IPphMedicine;

  constructor(
    protected pphMedicineService: PphMedicineService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.pphMedicineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('pphMedicineListModification');
      this.activeModal.close();
    });
  }
}

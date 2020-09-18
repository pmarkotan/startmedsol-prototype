import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPphMedicineQualifiedName } from 'app/shared/model/pph-medicine-qualified-name.model';
import { PphMedicineQualifiedNameService } from './pph-medicine-qualified-name.service';

@Component({
  templateUrl: './pph-medicine-qualified-name-delete-dialog.component.html',
})
export class PphMedicineQualifiedNameDeleteDialogComponent {
  pphMedicineQualifiedName?: IPphMedicineQualifiedName;

  constructor(
    protected pphMedicineQualifiedNameService: PphMedicineQualifiedNameService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.pphMedicineQualifiedNameService.delete(id).subscribe(() => {
      this.eventManager.broadcast('pphMedicineQualifiedNameListModification');
      this.activeModal.close();
    });
  }
}

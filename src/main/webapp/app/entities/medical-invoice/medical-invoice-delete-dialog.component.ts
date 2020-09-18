import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedicalInvoice } from 'app/shared/model/medical-invoice.model';
import { MedicalInvoiceService } from './medical-invoice.service';

@Component({
  templateUrl: './medical-invoice-delete-dialog.component.html',
})
export class MedicalInvoiceDeleteDialogComponent {
  medicalInvoice?: IMedicalInvoice;

  constructor(
    protected medicalInvoiceService: MedicalInvoiceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.medicalInvoiceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('medicalInvoiceListModification');
      this.activeModal.close();
    });
  }
}

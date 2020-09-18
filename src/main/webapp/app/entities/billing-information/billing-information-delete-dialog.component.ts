import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBillingInformation } from 'app/shared/model/billing-information.model';
import { BillingInformationService } from './billing-information.service';

@Component({
  templateUrl: './billing-information-delete-dialog.component.html',
})
export class BillingInformationDeleteDialogComponent {
  billingInformation?: IBillingInformation;

  constructor(
    protected billingInformationService: BillingInformationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.billingInformationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('billingInformationListModification');
      this.activeModal.close();
    });
  }
}

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICsPostalCode } from 'app/shared/model/cs-postal-code.model';
import { CsPostalCodeService } from './cs-postal-code.service';

@Component({
  templateUrl: './cs-postal-code-delete-dialog.component.html',
})
export class CsPostalCodeDeleteDialogComponent {
  csPostalCode?: ICsPostalCode;

  constructor(
    protected csPostalCodeService: CsPostalCodeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.csPostalCodeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('csPostalCodeListModification');
      this.activeModal.close();
    });
  }
}

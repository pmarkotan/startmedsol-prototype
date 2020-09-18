import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICsCountry } from 'app/shared/model/cs-country.model';
import { CsCountryService } from './cs-country.service';

@Component({
  templateUrl: './cs-country-delete-dialog.component.html',
})
export class CsCountryDeleteDialogComponent {
  csCountry?: ICsCountry;

  constructor(protected csCountryService: CsCountryService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.csCountryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('csCountryListModification');
      this.activeModal.close();
    });
  }
}

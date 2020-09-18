import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IContactPerson } from 'app/shared/model/contact-person.model';
import { ContactPersonService } from './contact-person.service';

@Component({
  templateUrl: './contact-person-delete-dialog.component.html',
})
export class ContactPersonDeleteDialogComponent {
  contactPerson?: IContactPerson;

  constructor(
    protected contactPersonService: ContactPersonService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.contactPersonService.delete(id).subscribe(() => {
      this.eventManager.broadcast('contactPersonListModification');
      this.activeModal.close();
    });
  }
}

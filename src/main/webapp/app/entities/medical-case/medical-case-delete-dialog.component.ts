import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedicalCase } from 'app/shared/model/medical-case.model';
import { MedicalCaseService } from './medical-case.service';

@Component({
  templateUrl: './medical-case-delete-dialog.component.html',
})
export class MedicalCaseDeleteDialogComponent {
  medicalCase?: IMedicalCase;

  constructor(
    protected medicalCaseService: MedicalCaseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.medicalCaseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('medicalCaseListModification');
      this.activeModal.close();
    });
  }
}

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICsDiagnosis } from 'app/shared/model/cs-diagnosis.model';
import { CsDiagnosisService } from './cs-diagnosis.service';

@Component({
  templateUrl: './cs-diagnosis-delete-dialog.component.html',
})
export class CsDiagnosisDeleteDialogComponent {
  csDiagnosis?: ICsDiagnosis;

  constructor(
    protected csDiagnosisService: CsDiagnosisService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.csDiagnosisService.delete(id).subscribe(() => {
      this.eventManager.broadcast('csDiagnosisListModification');
      this.activeModal.close();
    });
  }
}

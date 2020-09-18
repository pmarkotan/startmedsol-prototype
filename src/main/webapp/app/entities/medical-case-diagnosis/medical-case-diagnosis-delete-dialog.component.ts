import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedicalCaseDiagnosis } from 'app/shared/model/medical-case-diagnosis.model';
import { MedicalCaseDiagnosisService } from './medical-case-diagnosis.service';

@Component({
  templateUrl: './medical-case-diagnosis-delete-dialog.component.html',
})
export class MedicalCaseDiagnosisDeleteDialogComponent {
  medicalCaseDiagnosis?: IMedicalCaseDiagnosis;

  constructor(
    protected medicalCaseDiagnosisService: MedicalCaseDiagnosisService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.medicalCaseDiagnosisService.delete(id).subscribe(() => {
      this.eventManager.broadcast('medicalCaseDiagnosisListModification');
      this.activeModal.close();
    });
  }
}

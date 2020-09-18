import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IErrorRecord } from 'app/shared/model/error-record.model';
import { ErrorRecordService } from './error-record.service';

@Component({
  templateUrl: './error-record-delete-dialog.component.html',
})
export class ErrorRecordDeleteDialogComponent {
  errorRecord?: IErrorRecord;

  constructor(
    protected errorRecordService: ErrorRecordService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.errorRecordService.delete(id).subscribe(() => {
      this.eventManager.broadcast('errorRecordListModification');
      this.activeModal.close();
    });
  }
}

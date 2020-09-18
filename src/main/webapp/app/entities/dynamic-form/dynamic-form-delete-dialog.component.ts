import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDynamicForm } from 'app/shared/model/dynamic-form.model';
import { DynamicFormService } from './dynamic-form.service';

@Component({
  templateUrl: './dynamic-form-delete-dialog.component.html',
})
export class DynamicFormDeleteDialogComponent {
  dynamicForm?: IDynamicForm;

  constructor(
    protected dynamicFormService: DynamicFormService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dynamicFormService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dynamicFormListModification');
      this.activeModal.close();
    });
  }
}

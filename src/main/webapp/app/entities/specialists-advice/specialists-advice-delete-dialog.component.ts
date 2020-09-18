import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISpecialistsAdvice } from 'app/shared/model/specialists-advice.model';
import { SpecialistsAdviceService } from './specialists-advice.service';

@Component({
  templateUrl: './specialists-advice-delete-dialog.component.html',
})
export class SpecialistsAdviceDeleteDialogComponent {
  specialistsAdvice?: ISpecialistsAdvice;

  constructor(
    protected specialistsAdviceService: SpecialistsAdviceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.specialistsAdviceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('specialistsAdviceListModification');
      this.activeModal.close();
    });
  }
}

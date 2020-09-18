import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPuphaLoader } from 'app/shared/model/pupha-loader.model';
import { PuphaLoaderService } from './pupha-loader.service';

@Component({
  templateUrl: './pupha-loader-delete-dialog.component.html',
})
export class PuphaLoaderDeleteDialogComponent {
  puphaLoader?: IPuphaLoader;

  constructor(
    protected puphaLoaderService: PuphaLoaderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.puphaLoaderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('puphaLoaderListModification');
      this.activeModal.close();
    });
  }
}

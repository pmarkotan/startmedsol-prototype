import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDataGenerator } from 'app/shared/model/data-generator.model';
import { DataGeneratorService } from './data-generator.service';

@Component({
  templateUrl: './data-generator-delete-dialog.component.html',
})
export class DataGeneratorDeleteDialogComponent {
  dataGenerator?: IDataGenerator;

  constructor(
    protected dataGeneratorService: DataGeneratorService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dataGeneratorService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dataGeneratorListModification');
      this.activeModal.close();
    });
  }
}

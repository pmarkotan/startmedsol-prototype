import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStatistics } from 'app/shared/model/statistics.model';
import { StatisticsService } from './statistics.service';

@Component({
  templateUrl: './statistics-delete-dialog.component.html',
})
export class StatisticsDeleteDialogComponent {
  statistics?: IStatistics;

  constructor(
    protected statisticsService: StatisticsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.statisticsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('statisticsListModification');
      this.activeModal.close();
    });
  }
}

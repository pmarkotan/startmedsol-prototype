import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPrescriptionEesztId } from 'app/shared/model/prescription-eeszt-id.model';
import { PrescriptionEesztIdService } from './prescription-eeszt-id.service';
import { PrescriptionEesztIdDeleteDialogComponent } from './prescription-eeszt-id-delete-dialog.component';

@Component({
  selector: 'jhi-prescription-eeszt-id',
  templateUrl: './prescription-eeszt-id.component.html',
})
export class PrescriptionEesztIdComponent implements OnInit, OnDestroy {
  prescriptionEesztIds?: IPrescriptionEesztId[];
  eventSubscriber?: Subscription;

  constructor(
    protected prescriptionEesztIdService: PrescriptionEesztIdService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.prescriptionEesztIdService
      .query()
      .subscribe((res: HttpResponse<IPrescriptionEesztId[]>) => (this.prescriptionEesztIds = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPrescriptionEesztIds();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPrescriptionEesztId): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPrescriptionEesztIds(): void {
    this.eventSubscriber = this.eventManager.subscribe('prescriptionEesztIdListModification', () => this.loadAll());
  }

  delete(prescriptionEesztId: IPrescriptionEesztId): void {
    const modalRef = this.modalService.open(PrescriptionEesztIdDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.prescriptionEesztId = prescriptionEesztId;
  }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPphMedicineQualifiedName } from 'app/shared/model/pph-medicine-qualified-name.model';
import { PphMedicineQualifiedNameService } from './pph-medicine-qualified-name.service';
import { PphMedicineQualifiedNameDeleteDialogComponent } from './pph-medicine-qualified-name-delete-dialog.component';

@Component({
  selector: 'jhi-pph-medicine-qualified-name',
  templateUrl: './pph-medicine-qualified-name.component.html',
})
export class PphMedicineQualifiedNameComponent implements OnInit, OnDestroy {
  pphMedicineQualifiedNames?: IPphMedicineQualifiedName[];
  eventSubscriber?: Subscription;

  constructor(
    protected pphMedicineQualifiedNameService: PphMedicineQualifiedNameService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.pphMedicineQualifiedNameService
      .query()
      .subscribe((res: HttpResponse<IPphMedicineQualifiedName[]>) => (this.pphMedicineQualifiedNames = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPphMedicineQualifiedNames();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPphMedicineQualifiedName): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPphMedicineQualifiedNames(): void {
    this.eventSubscriber = this.eventManager.subscribe('pphMedicineQualifiedNameListModification', () => this.loadAll());
  }

  delete(pphMedicineQualifiedName: IPphMedicineQualifiedName): void {
    const modalRef = this.modalService.open(PphMedicineQualifiedNameDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.pphMedicineQualifiedName = pphMedicineQualifiedName;
  }
}

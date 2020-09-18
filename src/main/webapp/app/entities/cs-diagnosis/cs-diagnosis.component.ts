import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICsDiagnosis } from 'app/shared/model/cs-diagnosis.model';
import { CsDiagnosisService } from './cs-diagnosis.service';
import { CsDiagnosisDeleteDialogComponent } from './cs-diagnosis-delete-dialog.component';

@Component({
  selector: 'jhi-cs-diagnosis',
  templateUrl: './cs-diagnosis.component.html',
})
export class CsDiagnosisComponent implements OnInit, OnDestroy {
  csDiagnoses?: ICsDiagnosis[];
  eventSubscriber?: Subscription;

  constructor(
    protected csDiagnosisService: CsDiagnosisService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.csDiagnosisService.query().subscribe((res: HttpResponse<ICsDiagnosis[]>) => (this.csDiagnoses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCsDiagnoses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICsDiagnosis): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCsDiagnoses(): void {
    this.eventSubscriber = this.eventManager.subscribe('csDiagnosisListModification', () => this.loadAll());
  }

  delete(csDiagnosis: ICsDiagnosis): void {
    const modalRef = this.modalService.open(CsDiagnosisDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.csDiagnosis = csDiagnosis;
  }
}

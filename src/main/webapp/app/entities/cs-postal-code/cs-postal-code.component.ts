import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICsPostalCode } from 'app/shared/model/cs-postal-code.model';
import { CsPostalCodeService } from './cs-postal-code.service';
import { CsPostalCodeDeleteDialogComponent } from './cs-postal-code-delete-dialog.component';

@Component({
  selector: 'jhi-cs-postal-code',
  templateUrl: './cs-postal-code.component.html',
})
export class CsPostalCodeComponent implements OnInit, OnDestroy {
  csPostalCodes?: ICsPostalCode[];
  eventSubscriber?: Subscription;

  constructor(
    protected csPostalCodeService: CsPostalCodeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.csPostalCodeService.query().subscribe((res: HttpResponse<ICsPostalCode[]>) => (this.csPostalCodes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCsPostalCodes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICsPostalCode): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCsPostalCodes(): void {
    this.eventSubscriber = this.eventManager.subscribe('csPostalCodeListModification', () => this.loadAll());
  }

  delete(csPostalCode: ICsPostalCode): void {
    const modalRef = this.modalService.open(CsPostalCodeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.csPostalCode = csPostalCode;
  }
}

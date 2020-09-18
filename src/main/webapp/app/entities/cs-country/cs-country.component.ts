import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICsCountry } from 'app/shared/model/cs-country.model';
import { CsCountryService } from './cs-country.service';
import { CsCountryDeleteDialogComponent } from './cs-country-delete-dialog.component';

@Component({
  selector: 'jhi-cs-country',
  templateUrl: './cs-country.component.html',
})
export class CsCountryComponent implements OnInit, OnDestroy {
  csCountries?: ICsCountry[];
  eventSubscriber?: Subscription;

  constructor(protected csCountryService: CsCountryService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.csCountryService.query().subscribe((res: HttpResponse<ICsCountry[]>) => (this.csCountries = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCsCountries();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICsCountry): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCsCountries(): void {
    this.eventSubscriber = this.eventManager.subscribe('csCountryListModification', () => this.loadAll());
  }

  delete(csCountry: ICsCountry): void {
    const modalRef = this.modalService.open(CsCountryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.csCountry = csCountry;
  }
}

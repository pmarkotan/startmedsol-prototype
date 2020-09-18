import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICodeSetLoad } from 'app/shared/model/code-set-load.model';
import { CodeSetLoadService } from './code-set-load.service';
import { CodeSetLoadDeleteDialogComponent } from './code-set-load-delete-dialog.component';

@Component({
  selector: 'jhi-code-set-load',
  templateUrl: './code-set-load.component.html',
})
export class CodeSetLoadComponent implements OnInit, OnDestroy {
  codeSetLoads?: ICodeSetLoad[];
  eventSubscriber?: Subscription;

  constructor(
    protected codeSetLoadService: CodeSetLoadService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.codeSetLoadService.query().subscribe((res: HttpResponse<ICodeSetLoad[]>) => (this.codeSetLoads = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCodeSetLoads();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICodeSetLoad): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCodeSetLoads(): void {
    this.eventSubscriber = this.eventManager.subscribe('codeSetLoadListModification', () => this.loadAll());
  }

  delete(codeSetLoad: ICodeSetLoad): void {
    const modalRef = this.modalService.open(CodeSetLoadDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.codeSetLoad = codeSetLoad;
  }
}

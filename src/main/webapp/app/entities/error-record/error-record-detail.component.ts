import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IErrorRecord } from 'app/shared/model/error-record.model';

@Component({
  selector: 'jhi-error-record-detail',
  templateUrl: './error-record-detail.component.html',
})
export class ErrorRecordDetailComponent implements OnInit {
  errorRecord: IErrorRecord | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ errorRecord }) => (this.errorRecord = errorRecord));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}

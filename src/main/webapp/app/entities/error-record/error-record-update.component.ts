import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IErrorRecord, ErrorRecord } from 'app/shared/model/error-record.model';
import { ErrorRecordService } from './error-record.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-error-record-update',
  templateUrl: './error-record-update.component.html',
})
export class ErrorRecordUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    createDate: [null, [Validators.required]],
    content: [null, [Validators.required]],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected errorRecordService: ErrorRecordService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ errorRecord }) => {
      if (!errorRecord.id) {
        const today = moment().startOf('day');
        errorRecord.createDate = today;
      }

      this.updateForm(errorRecord);
    });
  }

  updateForm(errorRecord: IErrorRecord): void {
    this.editForm.patchValue({
      id: errorRecord.id,
      createDate: errorRecord.createDate ? errorRecord.createDate.format(DATE_TIME_FORMAT) : null,
      content: errorRecord.content,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('startMedsolPrototypeApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const errorRecord = this.createFromForm();
    if (errorRecord.id !== undefined) {
      this.subscribeToSaveResponse(this.errorRecordService.update(errorRecord));
    } else {
      this.subscribeToSaveResponse(this.errorRecordService.create(errorRecord));
    }
  }

  private createFromForm(): IErrorRecord {
    return {
      ...new ErrorRecord(),
      id: this.editForm.get(['id'])!.value,
      createDate: this.editForm.get(['createDate'])!.value ? moment(this.editForm.get(['createDate'])!.value, DATE_TIME_FORMAT) : undefined,
      content: this.editForm.get(['content'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IErrorRecord>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}

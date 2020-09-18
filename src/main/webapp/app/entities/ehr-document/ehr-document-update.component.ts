import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEhrDocument, EhrDocument } from 'app/shared/model/ehr-document.model';
import { EhrDocumentService } from './ehr-document.service';

@Component({
  selector: 'jhi-ehr-document-update',
  templateUrl: './ehr-document-update.component.html',
})
export class EhrDocumentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    eesztId: [],
    documentId: [],
    created: [],
    documentType: [],
    doctorName: [],
    institutionName: [],
    praxisName: [],
  });

  constructor(protected ehrDocumentService: EhrDocumentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ehrDocument }) => {
      if (!ehrDocument.id) {
        const today = moment().startOf('day');
        ehrDocument.created = today;
      }

      this.updateForm(ehrDocument);
    });
  }

  updateForm(ehrDocument: IEhrDocument): void {
    this.editForm.patchValue({
      id: ehrDocument.id,
      eesztId: ehrDocument.eesztId,
      documentId: ehrDocument.documentId,
      created: ehrDocument.created ? ehrDocument.created.format(DATE_TIME_FORMAT) : null,
      documentType: ehrDocument.documentType,
      doctorName: ehrDocument.doctorName,
      institutionName: ehrDocument.institutionName,
      praxisName: ehrDocument.praxisName,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ehrDocument = this.createFromForm();
    if (ehrDocument.id !== undefined) {
      this.subscribeToSaveResponse(this.ehrDocumentService.update(ehrDocument));
    } else {
      this.subscribeToSaveResponse(this.ehrDocumentService.create(ehrDocument));
    }
  }

  private createFromForm(): IEhrDocument {
    return {
      ...new EhrDocument(),
      id: this.editForm.get(['id'])!.value,
      eesztId: this.editForm.get(['eesztId'])!.value,
      documentId: this.editForm.get(['documentId'])!.value,
      created: this.editForm.get(['created'])!.value ? moment(this.editForm.get(['created'])!.value, DATE_TIME_FORMAT) : undefined,
      documentType: this.editForm.get(['documentType'])!.value,
      doctorName: this.editForm.get(['doctorName'])!.value,
      institutionName: this.editForm.get(['institutionName'])!.value,
      praxisName: this.editForm.get(['praxisName'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEhrDocument>>): void {
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

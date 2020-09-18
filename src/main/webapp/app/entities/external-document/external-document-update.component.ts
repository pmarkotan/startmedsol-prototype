import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IExternalDocument, ExternalDocument } from 'app/shared/model/external-document.model';
import { ExternalDocumentService } from './external-document.service';
import { IMedicalCase } from 'app/shared/model/medical-case.model';
import { MedicalCaseService } from 'app/entities/medical-case/medical-case.service';

@Component({
  selector: 'jhi-external-document-update',
  templateUrl: './external-document-update.component.html',
})
export class ExternalDocumentUpdateComponent implements OnInit {
  isSaving = false;
  medicalcases: IMedicalCase[] = [];

  editForm = this.fb.group({
    id: [],
    documentId: [null, [Validators.required]],
    name: [null, [Validators.required]],
    created: [],
    uploaded: [],
    medicalCaseId: [],
  });

  constructor(
    protected externalDocumentService: ExternalDocumentService,
    protected medicalCaseService: MedicalCaseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ externalDocument }) => {
      if (!externalDocument.id) {
        const today = moment().startOf('day');
        externalDocument.created = today;
        externalDocument.uploaded = today;
      }

      this.updateForm(externalDocument);

      this.medicalCaseService.query().subscribe((res: HttpResponse<IMedicalCase[]>) => (this.medicalcases = res.body || []));
    });
  }

  updateForm(externalDocument: IExternalDocument): void {
    this.editForm.patchValue({
      id: externalDocument.id,
      documentId: externalDocument.documentId,
      name: externalDocument.name,
      created: externalDocument.created ? externalDocument.created.format(DATE_TIME_FORMAT) : null,
      uploaded: externalDocument.uploaded ? externalDocument.uploaded.format(DATE_TIME_FORMAT) : null,
      medicalCaseId: externalDocument.medicalCaseId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const externalDocument = this.createFromForm();
    if (externalDocument.id !== undefined) {
      this.subscribeToSaveResponse(this.externalDocumentService.update(externalDocument));
    } else {
      this.subscribeToSaveResponse(this.externalDocumentService.create(externalDocument));
    }
  }

  private createFromForm(): IExternalDocument {
    return {
      ...new ExternalDocument(),
      id: this.editForm.get(['id'])!.value,
      documentId: this.editForm.get(['documentId'])!.value,
      name: this.editForm.get(['name'])!.value,
      created: this.editForm.get(['created'])!.value ? moment(this.editForm.get(['created'])!.value, DATE_TIME_FORMAT) : undefined,
      uploaded: this.editForm.get(['uploaded'])!.value ? moment(this.editForm.get(['uploaded'])!.value, DATE_TIME_FORMAT) : undefined,
      medicalCaseId: this.editForm.get(['medicalCaseId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExternalDocument>>): void {
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

  trackById(index: number, item: IMedicalCase): any {
    return item.id;
  }
}

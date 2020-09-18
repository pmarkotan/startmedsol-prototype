import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ICaseTextDocumentation, CaseTextDocumentation } from 'app/shared/model/case-text-documentation.model';
import { CaseTextDocumentationService } from './case-text-documentation.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-case-text-documentation-update',
  templateUrl: './case-text-documentation-update.component.html',
})
export class CaseTextDocumentationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    text: [],
    type: [null, [Validators.required]],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected caseTextDocumentationService: CaseTextDocumentationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ caseTextDocumentation }) => {
      this.updateForm(caseTextDocumentation);
    });
  }

  updateForm(caseTextDocumentation: ICaseTextDocumentation): void {
    this.editForm.patchValue({
      id: caseTextDocumentation.id,
      text: caseTextDocumentation.text,
      type: caseTextDocumentation.type,
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
    const caseTextDocumentation = this.createFromForm();
    if (caseTextDocumentation.id !== undefined) {
      this.subscribeToSaveResponse(this.caseTextDocumentationService.update(caseTextDocumentation));
    } else {
      this.subscribeToSaveResponse(this.caseTextDocumentationService.create(caseTextDocumentation));
    }
  }

  private createFromForm(): ICaseTextDocumentation {
    return {
      ...new CaseTextDocumentation(),
      id: this.editForm.get(['id'])!.value,
      text: this.editForm.get(['text'])!.value,
      type: this.editForm.get(['type'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICaseTextDocumentation>>): void {
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

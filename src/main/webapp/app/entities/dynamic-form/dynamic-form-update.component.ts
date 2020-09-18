import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IDynamicForm, DynamicForm } from 'app/shared/model/dynamic-form.model';
import { DynamicFormService } from './dynamic-form.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IProvider } from 'app/shared/model/provider.model';
import { ProviderService } from 'app/entities/provider/provider.service';

@Component({
  selector: 'jhi-dynamic-form-update',
  templateUrl: './dynamic-form-update.component.html',
})
export class DynamicFormUpdateComponent implements OnInit {
  isSaving = false;
  providers: IProvider[] = [];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.maxLength(255)]],
    name: [null, [Validators.required, Validators.maxLength(255)]],
    professionCode: [null, [Validators.required, Validators.maxLength(4)]],
    professionName: [null, [Validators.required, Validators.maxLength(50)]],
    formTemplate: [],
    reportTemplate: [],
    separatelyPrint: [],
    providerId: [null, Validators.required],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected dynamicFormService: DynamicFormService,
    protected providerService: ProviderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dynamicForm }) => {
      this.updateForm(dynamicForm);

      this.providerService.query().subscribe((res: HttpResponse<IProvider[]>) => (this.providers = res.body || []));
    });
  }

  updateForm(dynamicForm: IDynamicForm): void {
    this.editForm.patchValue({
      id: dynamicForm.id,
      code: dynamicForm.code,
      name: dynamicForm.name,
      professionCode: dynamicForm.professionCode,
      professionName: dynamicForm.professionName,
      formTemplate: dynamicForm.formTemplate,
      reportTemplate: dynamicForm.reportTemplate,
      separatelyPrint: dynamicForm.separatelyPrint,
      providerId: dynamicForm.providerId,
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
    const dynamicForm = this.createFromForm();
    if (dynamicForm.id !== undefined) {
      this.subscribeToSaveResponse(this.dynamicFormService.update(dynamicForm));
    } else {
      this.subscribeToSaveResponse(this.dynamicFormService.create(dynamicForm));
    }
  }

  private createFromForm(): IDynamicForm {
    return {
      ...new DynamicForm(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      name: this.editForm.get(['name'])!.value,
      professionCode: this.editForm.get(['professionCode'])!.value,
      professionName: this.editForm.get(['professionName'])!.value,
      formTemplate: this.editForm.get(['formTemplate'])!.value,
      reportTemplate: this.editForm.get(['reportTemplate'])!.value,
      separatelyPrint: this.editForm.get(['separatelyPrint'])!.value,
      providerId: this.editForm.get(['providerId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDynamicForm>>): void {
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

  trackById(index: number, item: IProvider): any {
    return item.id;
  }
}

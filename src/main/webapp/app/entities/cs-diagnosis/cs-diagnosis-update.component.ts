import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICsDiagnosis, CsDiagnosis } from 'app/shared/model/cs-diagnosis.model';
import { CsDiagnosisService } from './cs-diagnosis.service';
import { IValidity } from 'app/shared/model/validity.model';
import { ValidityService } from 'app/entities/validity/validity.service';

@Component({
  selector: 'jhi-cs-diagnosis-update',
  templateUrl: './cs-diagnosis-update.component.html',
})
export class CsDiagnosisUpdateComponent implements OnInit {
  isSaving = false;
  validities: IValidity[] = [];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.maxLength(10)]],
    report: [null, [Validators.required]],
    descr: [null, [Validators.required]],
    sex: [null, [Validators.required]],
    ageMin: [null, [Validators.min(0), Validators.max(99)]],
    ageMax: [null, [Validators.min(0), Validators.max(99)]],
    validityId: [null, Validators.required],
  });

  constructor(
    protected csDiagnosisService: CsDiagnosisService,
    protected validityService: ValidityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ csDiagnosis }) => {
      this.updateForm(csDiagnosis);

      this.validityService.query().subscribe((res: HttpResponse<IValidity[]>) => (this.validities = res.body || []));
    });
  }

  updateForm(csDiagnosis: ICsDiagnosis): void {
    this.editForm.patchValue({
      id: csDiagnosis.id,
      code: csDiagnosis.code,
      report: csDiagnosis.report,
      descr: csDiagnosis.descr,
      sex: csDiagnosis.sex,
      ageMin: csDiagnosis.ageMin,
      ageMax: csDiagnosis.ageMax,
      validityId: csDiagnosis.validityId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const csDiagnosis = this.createFromForm();
    if (csDiagnosis.id !== undefined) {
      this.subscribeToSaveResponse(this.csDiagnosisService.update(csDiagnosis));
    } else {
      this.subscribeToSaveResponse(this.csDiagnosisService.create(csDiagnosis));
    }
  }

  private createFromForm(): ICsDiagnosis {
    return {
      ...new CsDiagnosis(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      report: this.editForm.get(['report'])!.value,
      descr: this.editForm.get(['descr'])!.value,
      sex: this.editForm.get(['sex'])!.value,
      ageMin: this.editForm.get(['ageMin'])!.value,
      ageMax: this.editForm.get(['ageMax'])!.value,
      validityId: this.editForm.get(['validityId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICsDiagnosis>>): void {
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

  trackById(index: number, item: IValidity): any {
    return item.id;
  }
}

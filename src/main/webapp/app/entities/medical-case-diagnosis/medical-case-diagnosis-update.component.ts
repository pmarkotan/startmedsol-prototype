import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMedicalCaseDiagnosis, MedicalCaseDiagnosis } from 'app/shared/model/medical-case-diagnosis.model';
import { MedicalCaseDiagnosisService } from './medical-case-diagnosis.service';
import { ICsDiagnosis } from 'app/shared/model/cs-diagnosis.model';
import { CsDiagnosisService } from 'app/entities/cs-diagnosis/cs-diagnosis.service';
import { IMedicalCase } from 'app/shared/model/medical-case.model';
import { MedicalCaseService } from 'app/entities/medical-case/medical-case.service';

type SelectableEntity = ICsDiagnosis | IMedicalCase;

@Component({
  selector: 'jhi-medical-case-diagnosis-update',
  templateUrl: './medical-case-diagnosis-update.component.html',
})
export class MedicalCaseDiagnosisUpdateComponent implements OnInit {
  isSaving = false;
  csdiagnoses: ICsDiagnosis[] = [];
  medicalcases: IMedicalCase[] = [];

  editForm = this.fb.group({
    id: [],
    diagnosisId: [null, Validators.required],
    medicalCaseId: [null, Validators.required],
  });

  constructor(
    protected medicalCaseDiagnosisService: MedicalCaseDiagnosisService,
    protected csDiagnosisService: CsDiagnosisService,
    protected medicalCaseService: MedicalCaseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalCaseDiagnosis }) => {
      this.updateForm(medicalCaseDiagnosis);

      this.csDiagnosisService.query().subscribe((res: HttpResponse<ICsDiagnosis[]>) => (this.csdiagnoses = res.body || []));

      this.medicalCaseService.query().subscribe((res: HttpResponse<IMedicalCase[]>) => (this.medicalcases = res.body || []));
    });
  }

  updateForm(medicalCaseDiagnosis: IMedicalCaseDiagnosis): void {
    this.editForm.patchValue({
      id: medicalCaseDiagnosis.id,
      diagnosisId: medicalCaseDiagnosis.diagnosisId,
      medicalCaseId: medicalCaseDiagnosis.medicalCaseId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medicalCaseDiagnosis = this.createFromForm();
    if (medicalCaseDiagnosis.id !== undefined) {
      this.subscribeToSaveResponse(this.medicalCaseDiagnosisService.update(medicalCaseDiagnosis));
    } else {
      this.subscribeToSaveResponse(this.medicalCaseDiagnosisService.create(medicalCaseDiagnosis));
    }
  }

  private createFromForm(): IMedicalCaseDiagnosis {
    return {
      ...new MedicalCaseDiagnosis(),
      id: this.editForm.get(['id'])!.value,
      diagnosisId: this.editForm.get(['diagnosisId'])!.value,
      medicalCaseId: this.editForm.get(['medicalCaseId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedicalCaseDiagnosis>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}

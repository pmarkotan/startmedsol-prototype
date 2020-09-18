import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISpecialistsAdvice, SpecialistsAdvice } from 'app/shared/model/specialists-advice.model';
import { SpecialistsAdviceService } from './specialists-advice.service';
import { IMedicalCase } from 'app/shared/model/medical-case.model';
import { MedicalCaseService } from 'app/entities/medical-case/medical-case.service';
import { ICsDiagnosis } from 'app/shared/model/cs-diagnosis.model';
import { CsDiagnosisService } from 'app/entities/cs-diagnosis/cs-diagnosis.service';

type SelectableEntity = IMedicalCase | ICsDiagnosis;

@Component({
  selector: 'jhi-specialists-advice-update',
  templateUrl: './specialists-advice-update.component.html',
})
export class SpecialistsAdviceUpdateComponent implements OnInit {
  isSaving = false;
  medicalcases: IMedicalCase[] = [];
  csdiagnoses: ICsDiagnosis[] = [];

  editForm = this.fb.group({
    id: [],
    periodOfTime: [null, [Validators.maxLength(256)]],
    raisedIndicationCode: [null, [Validators.maxLength(64)]],
    raisedSubsidyPercent: [null, [Validators.maxLength(64)]],
    emphasizedIndicationCode: [null, [Validators.maxLength(64)]],
    activeSubstance: [null, [Validators.maxLength(256)]],
    efficacy: [null, [Validators.maxLength(256)]],
    dosageMod: [null, [Validators.maxLength(256)]],
    dosage: [null, [Validators.maxLength(256)]],
    medicalCaseId: [null, Validators.required],
    diagnosisId: [null, Validators.required],
  });

  constructor(
    protected specialistsAdviceService: SpecialistsAdviceService,
    protected medicalCaseService: MedicalCaseService,
    protected csDiagnosisService: CsDiagnosisService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ specialistsAdvice }) => {
      this.updateForm(specialistsAdvice);

      this.medicalCaseService.query().subscribe((res: HttpResponse<IMedicalCase[]>) => (this.medicalcases = res.body || []));

      this.csDiagnosisService.query().subscribe((res: HttpResponse<ICsDiagnosis[]>) => (this.csdiagnoses = res.body || []));
    });
  }

  updateForm(specialistsAdvice: ISpecialistsAdvice): void {
    this.editForm.patchValue({
      id: specialistsAdvice.id,
      periodOfTime: specialistsAdvice.periodOfTime,
      raisedIndicationCode: specialistsAdvice.raisedIndicationCode,
      raisedSubsidyPercent: specialistsAdvice.raisedSubsidyPercent,
      emphasizedIndicationCode: specialistsAdvice.emphasizedIndicationCode,
      activeSubstance: specialistsAdvice.activeSubstance,
      efficacy: specialistsAdvice.efficacy,
      dosageMod: specialistsAdvice.dosageMod,
      dosage: specialistsAdvice.dosage,
      medicalCaseId: specialistsAdvice.medicalCaseId,
      diagnosisId: specialistsAdvice.diagnosisId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const specialistsAdvice = this.createFromForm();
    if (specialistsAdvice.id !== undefined) {
      this.subscribeToSaveResponse(this.specialistsAdviceService.update(specialistsAdvice));
    } else {
      this.subscribeToSaveResponse(this.specialistsAdviceService.create(specialistsAdvice));
    }
  }

  private createFromForm(): ISpecialistsAdvice {
    return {
      ...new SpecialistsAdvice(),
      id: this.editForm.get(['id'])!.value,
      periodOfTime: this.editForm.get(['periodOfTime'])!.value,
      raisedIndicationCode: this.editForm.get(['raisedIndicationCode'])!.value,
      raisedSubsidyPercent: this.editForm.get(['raisedSubsidyPercent'])!.value,
      emphasizedIndicationCode: this.editForm.get(['emphasizedIndicationCode'])!.value,
      activeSubstance: this.editForm.get(['activeSubstance'])!.value,
      efficacy: this.editForm.get(['efficacy'])!.value,
      dosageMod: this.editForm.get(['dosageMod'])!.value,
      dosage: this.editForm.get(['dosage'])!.value,
      medicalCaseId: this.editForm.get(['medicalCaseId'])!.value,
      diagnosisId: this.editForm.get(['diagnosisId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpecialistsAdvice>>): void {
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

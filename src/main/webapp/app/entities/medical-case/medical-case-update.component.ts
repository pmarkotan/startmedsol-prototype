import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMedicalCase, MedicalCase } from 'app/shared/model/medical-case.model';
import { MedicalCaseService } from './medical-case.service';

@Component({
  selector: 'jhi-medical-case-update',
  templateUrl: './medical-case-update.component.html',
})
export class MedicalCaseUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    deleted: [null, [Validators.required]],
    confidental: [null, [Validators.required]],
    ambulentNumber: [null, [Validators.required, Validators.maxLength(12)]],
    admissionDate: [null, [Validators.required]],
    closeDate: [],
    status: [null, [Validators.required]],
    attendanceType: [null, [Validators.required]],
  });

  constructor(protected medicalCaseService: MedicalCaseService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalCase }) => {
      if (!medicalCase.id) {
        const today = moment().startOf('day');
        medicalCase.admissionDate = today;
        medicalCase.closeDate = today;
      }

      this.updateForm(medicalCase);
    });
  }

  updateForm(medicalCase: IMedicalCase): void {
    this.editForm.patchValue({
      id: medicalCase.id,
      deleted: medicalCase.deleted,
      confidental: medicalCase.confidental,
      ambulentNumber: medicalCase.ambulentNumber,
      admissionDate: medicalCase.admissionDate ? medicalCase.admissionDate.format(DATE_TIME_FORMAT) : null,
      closeDate: medicalCase.closeDate ? medicalCase.closeDate.format(DATE_TIME_FORMAT) : null,
      status: medicalCase.status,
      attendanceType: medicalCase.attendanceType,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medicalCase = this.createFromForm();
    if (medicalCase.id !== undefined) {
      this.subscribeToSaveResponse(this.medicalCaseService.update(medicalCase));
    } else {
      this.subscribeToSaveResponse(this.medicalCaseService.create(medicalCase));
    }
  }

  private createFromForm(): IMedicalCase {
    return {
      ...new MedicalCase(),
      id: this.editForm.get(['id'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      confidental: this.editForm.get(['confidental'])!.value,
      ambulentNumber: this.editForm.get(['ambulentNumber'])!.value,
      admissionDate: this.editForm.get(['admissionDate'])!.value
        ? moment(this.editForm.get(['admissionDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      closeDate: this.editForm.get(['closeDate'])!.value ? moment(this.editForm.get(['closeDate'])!.value, DATE_TIME_FORMAT) : undefined,
      status: this.editForm.get(['status'])!.value,
      attendanceType: this.editForm.get(['attendanceType'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedicalCase>>): void {
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

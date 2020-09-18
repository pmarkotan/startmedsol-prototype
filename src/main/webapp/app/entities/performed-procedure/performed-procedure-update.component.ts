import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPerformedProcedure, PerformedProcedure } from 'app/shared/model/performed-procedure.model';
import { PerformedProcedureService } from './performed-procedure.service';
import { ICsProcedure } from 'app/shared/model/cs-procedure.model';
import { CsProcedureService } from 'app/entities/cs-procedure/cs-procedure.service';
import { IMedicalCase } from 'app/shared/model/medical-case.model';
import { MedicalCaseService } from 'app/entities/medical-case/medical-case.service';

type SelectableEntity = ICsProcedure | IMedicalCase;

@Component({
  selector: 'jhi-performed-procedure-update',
  templateUrl: './performed-procedure-update.component.html',
})
export class PerformedProcedureUpdateComponent implements OnInit {
  isSaving = false;
  csprocedures: ICsProcedure[] = [];
  medicalcases: IMedicalCase[] = [];

  editForm = this.fb.group({
    id: [],
    procedureId: [null, Validators.required],
    medicalCaseId: [null, Validators.required],
  });

  constructor(
    protected performedProcedureService: PerformedProcedureService,
    protected csProcedureService: CsProcedureService,
    protected medicalCaseService: MedicalCaseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ performedProcedure }) => {
      this.updateForm(performedProcedure);

      this.csProcedureService.query().subscribe((res: HttpResponse<ICsProcedure[]>) => (this.csprocedures = res.body || []));

      this.medicalCaseService.query().subscribe((res: HttpResponse<IMedicalCase[]>) => (this.medicalcases = res.body || []));
    });
  }

  updateForm(performedProcedure: IPerformedProcedure): void {
    this.editForm.patchValue({
      id: performedProcedure.id,
      procedureId: performedProcedure.procedureId,
      medicalCaseId: performedProcedure.medicalCaseId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const performedProcedure = this.createFromForm();
    if (performedProcedure.id !== undefined) {
      this.subscribeToSaveResponse(this.performedProcedureService.update(performedProcedure));
    } else {
      this.subscribeToSaveResponse(this.performedProcedureService.create(performedProcedure));
    }
  }

  private createFromForm(): IPerformedProcedure {
    return {
      ...new PerformedProcedure(),
      id: this.editForm.get(['id'])!.value,
      procedureId: this.editForm.get(['procedureId'])!.value,
      medicalCaseId: this.editForm.get(['medicalCaseId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPerformedProcedure>>): void {
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

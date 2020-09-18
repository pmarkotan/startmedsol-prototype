import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICsProcedure, CsProcedure } from 'app/shared/model/cs-procedure.model';
import { CsProcedureService } from './cs-procedure.service';
import { IValidity } from 'app/shared/model/validity.model';
import { ValidityService } from 'app/entities/validity/validity.service';

@Component({
  selector: 'jhi-cs-procedure-update',
  templateUrl: './cs-procedure-update.component.html',
})
export class CsProcedureUpdateComponent implements OnInit {
  isSaving = false;
  validities: IValidity[] = [];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.maxLength(10)]],
    description: [null, [Validators.required]],
    validityId: [null, Validators.required],
  });

  constructor(
    protected csProcedureService: CsProcedureService,
    protected validityService: ValidityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ csProcedure }) => {
      this.updateForm(csProcedure);

      this.validityService.query().subscribe((res: HttpResponse<IValidity[]>) => (this.validities = res.body || []));
    });
  }

  updateForm(csProcedure: ICsProcedure): void {
    this.editForm.patchValue({
      id: csProcedure.id,
      code: csProcedure.code,
      description: csProcedure.description,
      validityId: csProcedure.validityId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const csProcedure = this.createFromForm();
    if (csProcedure.id !== undefined) {
      this.subscribeToSaveResponse(this.csProcedureService.update(csProcedure));
    } else {
      this.subscribeToSaveResponse(this.csProcedureService.create(csProcedure));
    }
  }

  private createFromForm(): ICsProcedure {
    return {
      ...new CsProcedure(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      description: this.editForm.get(['description'])!.value,
      validityId: this.editForm.get(['validityId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICsProcedure>>): void {
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

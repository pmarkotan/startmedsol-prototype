import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICsPostalCode, CsPostalCode } from 'app/shared/model/cs-postal-code.model';
import { CsPostalCodeService } from './cs-postal-code.service';
import { IValidity } from 'app/shared/model/validity.model';
import { ValidityService } from 'app/entities/validity/validity.service';

@Component({
  selector: 'jhi-cs-postal-code-update',
  templateUrl: './cs-postal-code-update.component.html',
})
export class CsPostalCodeUpdateComponent implements OnInit {
  isSaving = false;
  validities: IValidity[] = [];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.maxLength(5)]],
    settlement: [null, [Validators.required]],
    part: [],
    street: [],
    kind: [],
    rangeType: [null, [Validators.required]],
    rangeLo: [null, [Validators.min(0)]],
    rangeHi: [null, [Validators.min(0)]],
    district: [null, [Validators.maxLength(20)]],
    validityId: [null, Validators.required],
  });

  constructor(
    protected csPostalCodeService: CsPostalCodeService,
    protected validityService: ValidityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ csPostalCode }) => {
      this.updateForm(csPostalCode);

      this.validityService.query().subscribe((res: HttpResponse<IValidity[]>) => (this.validities = res.body || []));
    });
  }

  updateForm(csPostalCode: ICsPostalCode): void {
    this.editForm.patchValue({
      id: csPostalCode.id,
      code: csPostalCode.code,
      settlement: csPostalCode.settlement,
      part: csPostalCode.part,
      street: csPostalCode.street,
      kind: csPostalCode.kind,
      rangeType: csPostalCode.rangeType,
      rangeLo: csPostalCode.rangeLo,
      rangeHi: csPostalCode.rangeHi,
      district: csPostalCode.district,
      validityId: csPostalCode.validityId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const csPostalCode = this.createFromForm();
    if (csPostalCode.id !== undefined) {
      this.subscribeToSaveResponse(this.csPostalCodeService.update(csPostalCode));
    } else {
      this.subscribeToSaveResponse(this.csPostalCodeService.create(csPostalCode));
    }
  }

  private createFromForm(): ICsPostalCode {
    return {
      ...new CsPostalCode(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      settlement: this.editForm.get(['settlement'])!.value,
      part: this.editForm.get(['part'])!.value,
      street: this.editForm.get(['street'])!.value,
      kind: this.editForm.get(['kind'])!.value,
      rangeType: this.editForm.get(['rangeType'])!.value,
      rangeLo: this.editForm.get(['rangeLo'])!.value,
      rangeHi: this.editForm.get(['rangeHi'])!.value,
      district: this.editForm.get(['district'])!.value,
      validityId: this.editForm.get(['validityId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICsPostalCode>>): void {
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

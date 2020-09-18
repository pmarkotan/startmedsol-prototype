import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICsCountry, CsCountry } from 'app/shared/model/cs-country.model';
import { CsCountryService } from './cs-country.service';
import { IValidity } from 'app/shared/model/validity.model';
import { ValidityService } from 'app/entities/validity/validity.service';

@Component({
  selector: 'jhi-cs-country-update',
  templateUrl: './cs-country-update.component.html',
})
export class CsCountryUpdateComponent implements OnInit {
  isSaving = false;
  validities: IValidity[] = [];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.maxLength(4)]],
    description: [null, [Validators.required]],
    validityId: [null, Validators.required],
  });

  constructor(
    protected csCountryService: CsCountryService,
    protected validityService: ValidityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ csCountry }) => {
      this.updateForm(csCountry);

      this.validityService.query().subscribe((res: HttpResponse<IValidity[]>) => (this.validities = res.body || []));
    });
  }

  updateForm(csCountry: ICsCountry): void {
    this.editForm.patchValue({
      id: csCountry.id,
      code: csCountry.code,
      description: csCountry.description,
      validityId: csCountry.validityId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const csCountry = this.createFromForm();
    if (csCountry.id !== undefined) {
      this.subscribeToSaveResponse(this.csCountryService.update(csCountry));
    } else {
      this.subscribeToSaveResponse(this.csCountryService.create(csCountry));
    }
  }

  private createFromForm(): ICsCountry {
    return {
      ...new CsCountry(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      description: this.editForm.get(['description'])!.value,
      validityId: this.editForm.get(['validityId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICsCountry>>): void {
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

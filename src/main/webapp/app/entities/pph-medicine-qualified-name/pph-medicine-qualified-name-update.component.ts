import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPphMedicineQualifiedName, PphMedicineQualifiedName } from 'app/shared/model/pph-medicine-qualified-name.model';
import { PphMedicineQualifiedNameService } from './pph-medicine-qualified-name.service';

@Component({
  selector: 'jhi-pph-medicine-qualified-name-update',
  templateUrl: './pph-medicine-qualified-name-update.component.html',
})
export class PphMedicineQualifiedNameUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(250)]],
    activeSubstance: [null, [Validators.maxLength(128)]],
    activePuphaData: [null, [Validators.required]],
  });

  constructor(
    protected pphMedicineQualifiedNameService: PphMedicineQualifiedNameService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pphMedicineQualifiedName }) => {
      this.updateForm(pphMedicineQualifiedName);
    });
  }

  updateForm(pphMedicineQualifiedName: IPphMedicineQualifiedName): void {
    this.editForm.patchValue({
      id: pphMedicineQualifiedName.id,
      name: pphMedicineQualifiedName.name,
      activeSubstance: pphMedicineQualifiedName.activeSubstance,
      activePuphaData: pphMedicineQualifiedName.activePuphaData,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const pphMedicineQualifiedName = this.createFromForm();
    if (pphMedicineQualifiedName.id !== undefined) {
      this.subscribeToSaveResponse(this.pphMedicineQualifiedNameService.update(pphMedicineQualifiedName));
    } else {
      this.subscribeToSaveResponse(this.pphMedicineQualifiedNameService.create(pphMedicineQualifiedName));
    }
  }

  private createFromForm(): IPphMedicineQualifiedName {
    return {
      ...new PphMedicineQualifiedName(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      activeSubstance: this.editForm.get(['activeSubstance'])!.value,
      activePuphaData: this.editForm.get(['activePuphaData'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPphMedicineQualifiedName>>): void {
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

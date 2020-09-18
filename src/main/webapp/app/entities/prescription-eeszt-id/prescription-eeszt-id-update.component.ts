import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPrescriptionEesztId, PrescriptionEesztId } from 'app/shared/model/prescription-eeszt-id.model';
import { PrescriptionEesztIdService } from './prescription-eeszt-id.service';
import { IPrescription } from 'app/shared/model/prescription.model';
import { PrescriptionService } from 'app/entities/prescription/prescription.service';

@Component({
  selector: 'jhi-prescription-eeszt-id-update',
  templateUrl: './prescription-eeszt-id-update.component.html',
})
export class PrescriptionEesztIdUpdateComponent implements OnInit {
  isSaving = false;
  prescriptions: IPrescription[] = [];

  editForm = this.fb.group({
    id: [],
    eesztId: [null, [Validators.required, Validators.maxLength(36)]],
    eesztVersion: [],
    prescription: [null, Validators.required],
  });

  constructor(
    protected prescriptionEesztIdService: PrescriptionEesztIdService,
    protected prescriptionService: PrescriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ prescriptionEesztId }) => {
      this.updateForm(prescriptionEesztId);

      this.prescriptionService.query().subscribe((res: HttpResponse<IPrescription[]>) => (this.prescriptions = res.body || []));
    });
  }

  updateForm(prescriptionEesztId: IPrescriptionEesztId): void {
    this.editForm.patchValue({
      id: prescriptionEesztId.id,
      eesztId: prescriptionEesztId.eesztId,
      eesztVersion: prescriptionEesztId.eesztVersion,
      prescription: prescriptionEesztId.prescription,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const prescriptionEesztId = this.createFromForm();
    if (prescriptionEesztId.id !== undefined) {
      this.subscribeToSaveResponse(this.prescriptionEesztIdService.update(prescriptionEesztId));
    } else {
      this.subscribeToSaveResponse(this.prescriptionEesztIdService.create(prescriptionEesztId));
    }
  }

  private createFromForm(): IPrescriptionEesztId {
    return {
      ...new PrescriptionEesztId(),
      id: this.editForm.get(['id'])!.value,
      eesztId: this.editForm.get(['eesztId'])!.value,
      eesztVersion: this.editForm.get(['eesztVersion'])!.value,
      prescription: this.editForm.get(['prescription'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrescriptionEesztId>>): void {
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

  trackById(index: number, item: IPrescription): any {
    return item.id;
  }
}

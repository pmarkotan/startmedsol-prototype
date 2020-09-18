import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMedicalInvoice, MedicalInvoice } from 'app/shared/model/medical-invoice.model';
import { MedicalInvoiceService } from './medical-invoice.service';
import { IMedicalCase } from 'app/shared/model/medical-case.model';
import { MedicalCaseService } from 'app/entities/medical-case/medical-case.service';

@Component({
  selector: 'jhi-medical-invoice-update',
  templateUrl: './medical-invoice-update.component.html',
})
export class MedicalInvoiceUpdateComponent implements OnInit {
  isSaving = false;
  medicalcases: IMedicalCase[] = [];

  editForm = this.fb.group({
    id: [],
    orderNumber: [null, [Validators.required]],
    invoiceNumber: [null, [Validators.required, Validators.maxLength(100)]],
    type: [null, [Validators.required]],
    total: [null, [Validators.required]],
    creatorUser: [null, [Validators.required, Validators.maxLength(100)]],
    createdAt: [null, [Validators.required]],
    medicalCaseId: [null, Validators.required],
  });

  constructor(
    protected medicalInvoiceService: MedicalInvoiceService,
    protected medicalCaseService: MedicalCaseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalInvoice }) => {
      if (!medicalInvoice.id) {
        const today = moment().startOf('day');
        medicalInvoice.createdAt = today;
      }

      this.updateForm(medicalInvoice);

      this.medicalCaseService.query().subscribe((res: HttpResponse<IMedicalCase[]>) => (this.medicalcases = res.body || []));
    });
  }

  updateForm(medicalInvoice: IMedicalInvoice): void {
    this.editForm.patchValue({
      id: medicalInvoice.id,
      orderNumber: medicalInvoice.orderNumber,
      invoiceNumber: medicalInvoice.invoiceNumber,
      type: medicalInvoice.type,
      total: medicalInvoice.total,
      creatorUser: medicalInvoice.creatorUser,
      createdAt: medicalInvoice.createdAt ? medicalInvoice.createdAt.format(DATE_TIME_FORMAT) : null,
      medicalCaseId: medicalInvoice.medicalCaseId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medicalInvoice = this.createFromForm();
    if (medicalInvoice.id !== undefined) {
      this.subscribeToSaveResponse(this.medicalInvoiceService.update(medicalInvoice));
    } else {
      this.subscribeToSaveResponse(this.medicalInvoiceService.create(medicalInvoice));
    }
  }

  private createFromForm(): IMedicalInvoice {
    return {
      ...new MedicalInvoice(),
      id: this.editForm.get(['id'])!.value,
      orderNumber: this.editForm.get(['orderNumber'])!.value,
      invoiceNumber: this.editForm.get(['invoiceNumber'])!.value,
      type: this.editForm.get(['type'])!.value,
      total: this.editForm.get(['total'])!.value,
      creatorUser: this.editForm.get(['creatorUser'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      medicalCaseId: this.editForm.get(['medicalCaseId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedicalInvoice>>): void {
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

  trackById(index: number, item: IMedicalCase): any {
    return item.id;
  }
}

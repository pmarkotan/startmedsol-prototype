import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMedicalService, MedicalService } from 'app/shared/model/medical-service.model';
import { MedicalServiceService } from './medical-service.service';
import { IPraxis } from 'app/shared/model/praxis.model';
import { PraxisService } from 'app/entities/praxis/praxis.service';
import { IProvider } from 'app/shared/model/provider.model';
import { ProviderService } from 'app/entities/provider/provider.service';

type SelectableEntity = IPraxis | IProvider;

@Component({
  selector: 'jhi-medical-service-update',
  templateUrl: './medical-service-update.component.html',
})
export class MedicalServiceUpdateComponent implements OnInit {
  isSaving = false;
  praxes: IPraxis[] = [];
  providers: IProvider[] = [];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.maxLength(20)]],
    name: [null, [Validators.required, Validators.maxLength(100)]],
    grossPrice: [],
    unit: [],
    statisticalCode: [null, [Validators.maxLength(20)]],
    taxRate: [],
    praxisId: [null, Validators.required],
    providerId: [null, Validators.required],
  });

  constructor(
    protected medicalServiceService: MedicalServiceService,
    protected praxisService: PraxisService,
    protected providerService: ProviderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalService }) => {
      this.updateForm(medicalService);

      this.praxisService.query().subscribe((res: HttpResponse<IPraxis[]>) => (this.praxes = res.body || []));

      this.providerService.query().subscribe((res: HttpResponse<IProvider[]>) => (this.providers = res.body || []));
    });
  }

  updateForm(medicalService: IMedicalService): void {
    this.editForm.patchValue({
      id: medicalService.id,
      code: medicalService.code,
      name: medicalService.name,
      grossPrice: medicalService.grossPrice,
      unit: medicalService.unit,
      statisticalCode: medicalService.statisticalCode,
      taxRate: medicalService.taxRate,
      praxisId: medicalService.praxisId,
      providerId: medicalService.providerId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medicalService = this.createFromForm();
    if (medicalService.id !== undefined) {
      this.subscribeToSaveResponse(this.medicalServiceService.update(medicalService));
    } else {
      this.subscribeToSaveResponse(this.medicalServiceService.create(medicalService));
    }
  }

  private createFromForm(): IMedicalService {
    return {
      ...new MedicalService(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      name: this.editForm.get(['name'])!.value,
      grossPrice: this.editForm.get(['grossPrice'])!.value,
      unit: this.editForm.get(['unit'])!.value,
      statisticalCode: this.editForm.get(['statisticalCode'])!.value,
      taxRate: this.editForm.get(['taxRate'])!.value,
      praxisId: this.editForm.get(['praxisId'])!.value,
      providerId: this.editForm.get(['providerId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedicalService>>): void {
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

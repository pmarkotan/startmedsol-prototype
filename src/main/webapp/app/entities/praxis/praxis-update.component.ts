import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IPraxis, Praxis } from 'app/shared/model/praxis.model';
import { PraxisService } from './praxis.service';
import { IAddress } from 'app/shared/model/address.model';
import { AddressService } from 'app/entities/address/address.service';
import { IProvider } from 'app/shared/model/provider.model';
import { ProviderService } from 'app/entities/provider/provider.service';
import { IEmployee } from 'app/shared/model/employee.model';
import { EmployeeService } from 'app/entities/employee/employee.service';

type SelectableEntity = IAddress | IProvider | IEmployee;

@Component({
  selector: 'jhi-praxis-update',
  templateUrl: './praxis-update.component.html',
})
export class PraxisUpdateComponent implements OnInit {
  isSaving = false;
  officeaddresses: IAddress[] = [];
  providers: IProvider[] = [];
  employees: IEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(100)]],
    departmentId: [null, [Validators.required, Validators.maxLength(9), Validators.pattern('^[A-Z0-9]+$')]],
    professionCode: [null, [Validators.required, Validators.maxLength(4), Validators.pattern('^[A-Z0-9]+$')]],
    professionName: [null, [Validators.required, Validators.maxLength(50)]],
    status: [null, [Validators.required]],
    appointmentPhone: [null, [Validators.required, Validators.maxLength(16), Validators.pattern('^\\++\\d+$')]],
    treatmentLogbookNumber: [null, [Validators.required, Validators.min(1), Validators.max(99999999)]],
    officeAddressId: [],
    providerId: [],
    doctorId: [],
  });

  constructor(
    protected praxisService: PraxisService,
    protected addressService: AddressService,
    protected providerService: ProviderService,
    protected employeeService: EmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ praxis }) => {
      this.updateForm(praxis);

      this.addressService
        .query({ filter: 'praxis-is-null' })
        .pipe(
          map((res: HttpResponse<IAddress[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IAddress[]) => {
          if (!praxis.officeAddressId) {
            this.officeaddresses = resBody;
          } else {
            this.addressService
              .find(praxis.officeAddressId)
              .pipe(
                map((subRes: HttpResponse<IAddress>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IAddress[]) => (this.officeaddresses = concatRes));
          }
        });

      this.providerService.query().subscribe((res: HttpResponse<IProvider[]>) => (this.providers = res.body || []));

      this.employeeService.query().subscribe((res: HttpResponse<IEmployee[]>) => (this.employees = res.body || []));
    });
  }

  updateForm(praxis: IPraxis): void {
    this.editForm.patchValue({
      id: praxis.id,
      name: praxis.name,
      departmentId: praxis.departmentId,
      professionCode: praxis.professionCode,
      professionName: praxis.professionName,
      status: praxis.status,
      appointmentPhone: praxis.appointmentPhone,
      treatmentLogbookNumber: praxis.treatmentLogbookNumber,
      officeAddressId: praxis.officeAddressId,
      providerId: praxis.providerId,
      doctorId: praxis.doctorId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const praxis = this.createFromForm();
    if (praxis.id !== undefined) {
      this.subscribeToSaveResponse(this.praxisService.update(praxis));
    } else {
      this.subscribeToSaveResponse(this.praxisService.create(praxis));
    }
  }

  private createFromForm(): IPraxis {
    return {
      ...new Praxis(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      departmentId: this.editForm.get(['departmentId'])!.value,
      professionCode: this.editForm.get(['professionCode'])!.value,
      professionName: this.editForm.get(['professionName'])!.value,
      status: this.editForm.get(['status'])!.value,
      appointmentPhone: this.editForm.get(['appointmentPhone'])!.value,
      treatmentLogbookNumber: this.editForm.get(['treatmentLogbookNumber'])!.value,
      officeAddressId: this.editForm.get(['officeAddressId'])!.value,
      providerId: this.editForm.get(['providerId'])!.value,
      doctorId: this.editForm.get(['doctorId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPraxis>>): void {
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

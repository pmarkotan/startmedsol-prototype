import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IProvider, Provider } from 'app/shared/model/provider.model';
import { ProviderService } from './provider.service';
import { IContactPerson } from 'app/shared/model/contact-person.model';
import { ContactPersonService } from 'app/entities/contact-person/contact-person.service';
import { ICompany } from 'app/shared/model/company.model';
import { CompanyService } from 'app/entities/company/company.service';
import { IBillingInformation } from 'app/shared/model/billing-information.model';
import { BillingInformationService } from 'app/entities/billing-information/billing-information.service';
import { IEmployee } from 'app/shared/model/employee.model';
import { EmployeeService } from 'app/entities/employee/employee.service';

type SelectableEntity = IContactPerson | ICompany | IBillingInformation | IEmployee;

@Component({
  selector: 'jhi-provider-update',
  templateUrl: './provider-update.component.html',
})
export class ProviderUpdateComponent implements OnInit {
  isSaving = false;
  contactpeople: IContactPerson[] = [];
  companies: ICompany[] = [];
  billinginformations: IBillingInformation[] = [];
  employees: IEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    nameLong: [null, [Validators.required, Validators.maxLength(100)]],
    nameShort: [null, [Validators.maxLength(30)]],
    institutionId: [null, [Validators.required, Validators.maxLength(6), Validators.pattern('^[A-Z0-9]+$')]],
    email: [null, [Validators.required, Validators.maxLength(254), Validators.pattern('^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$')]],
    phone: [null, [Validators.required, Validators.maxLength(16), Validators.pattern('^\\++\\d+$')]],
    accountNumber: [null, [Validators.required]],
    contactPersonId: [],
    companyId: [],
    billingInformationId: [],
    employees: [],
  });

  constructor(
    protected providerService: ProviderService,
    protected contactPersonService: ContactPersonService,
    protected companyService: CompanyService,
    protected billingInformationService: BillingInformationService,
    protected employeeService: EmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ provider }) => {
      this.updateForm(provider);

      this.contactPersonService
        .query({ filter: 'provider-is-null' })
        .pipe(
          map((res: HttpResponse<IContactPerson[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IContactPerson[]) => {
          if (!provider.contactPersonId) {
            this.contactpeople = resBody;
          } else {
            this.contactPersonService
              .find(provider.contactPersonId)
              .pipe(
                map((subRes: HttpResponse<IContactPerson>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IContactPerson[]) => (this.contactpeople = concatRes));
          }
        });

      this.companyService
        .query({ filter: 'provider-is-null' })
        .pipe(
          map((res: HttpResponse<ICompany[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICompany[]) => {
          if (!provider.companyId) {
            this.companies = resBody;
          } else {
            this.companyService
              .find(provider.companyId)
              .pipe(
                map((subRes: HttpResponse<ICompany>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICompany[]) => (this.companies = concatRes));
          }
        });

      this.billingInformationService
        .query({ filter: 'provider-is-null' })
        .pipe(
          map((res: HttpResponse<IBillingInformation[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IBillingInformation[]) => {
          if (!provider.billingInformationId) {
            this.billinginformations = resBody;
          } else {
            this.billingInformationService
              .find(provider.billingInformationId)
              .pipe(
                map((subRes: HttpResponse<IBillingInformation>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IBillingInformation[]) => (this.billinginformations = concatRes));
          }
        });

      this.employeeService.query().subscribe((res: HttpResponse<IEmployee[]>) => (this.employees = res.body || []));
    });
  }

  updateForm(provider: IProvider): void {
    this.editForm.patchValue({
      id: provider.id,
      nameLong: provider.nameLong,
      nameShort: provider.nameShort,
      institutionId: provider.institutionId,
      email: provider.email,
      phone: provider.phone,
      accountNumber: provider.accountNumber,
      contactPersonId: provider.contactPersonId,
      companyId: provider.companyId,
      billingInformationId: provider.billingInformationId,
      employees: provider.employees,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const provider = this.createFromForm();
    if (provider.id !== undefined) {
      this.subscribeToSaveResponse(this.providerService.update(provider));
    } else {
      this.subscribeToSaveResponse(this.providerService.create(provider));
    }
  }

  private createFromForm(): IProvider {
    return {
      ...new Provider(),
      id: this.editForm.get(['id'])!.value,
      nameLong: this.editForm.get(['nameLong'])!.value,
      nameShort: this.editForm.get(['nameShort'])!.value,
      institutionId: this.editForm.get(['institutionId'])!.value,
      email: this.editForm.get(['email'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      accountNumber: this.editForm.get(['accountNumber'])!.value,
      contactPersonId: this.editForm.get(['contactPersonId'])!.value,
      companyId: this.editForm.get(['companyId'])!.value,
      billingInformationId: this.editForm.get(['billingInformationId'])!.value,
      employees: this.editForm.get(['employees'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProvider>>): void {
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

  getSelected(selectedVals: IEmployee[], option: IEmployee): IEmployee {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}

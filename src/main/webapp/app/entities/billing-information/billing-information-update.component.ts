import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IBillingInformation, BillingInformation } from 'app/shared/model/billing-information.model';
import { BillingInformationService } from './billing-information.service';
import { IAddress } from 'app/shared/model/address.model';
import { AddressService } from 'app/entities/address/address.service';

@Component({
  selector: 'jhi-billing-information-update',
  templateUrl: './billing-information-update.component.html',
})
export class BillingInformationUpdateComponent implements OnInit {
  isSaving = false;
  billingaddresses: IAddress[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(30)]],
    taxnumber: [null, [Validators.maxLength(13), Validators.pattern('^\\d{8}-\\d-\\d{2}$')]],
    billingAddressId: [],
  });

  constructor(
    protected billingInformationService: BillingInformationService,
    protected addressService: AddressService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ billingInformation }) => {
      this.updateForm(billingInformation);

      this.addressService
        .query({ filter: 'billinginformation-is-null' })
        .pipe(
          map((res: HttpResponse<IAddress[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IAddress[]) => {
          if (!billingInformation.billingAddressId) {
            this.billingaddresses = resBody;
          } else {
            this.addressService
              .find(billingInformation.billingAddressId)
              .pipe(
                map((subRes: HttpResponse<IAddress>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IAddress[]) => (this.billingaddresses = concatRes));
          }
        });
    });
  }

  updateForm(billingInformation: IBillingInformation): void {
    this.editForm.patchValue({
      id: billingInformation.id,
      name: billingInformation.name,
      taxnumber: billingInformation.taxnumber,
      billingAddressId: billingInformation.billingAddressId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const billingInformation = this.createFromForm();
    if (billingInformation.id !== undefined) {
      this.subscribeToSaveResponse(this.billingInformationService.update(billingInformation));
    } else {
      this.subscribeToSaveResponse(this.billingInformationService.create(billingInformation));
    }
  }

  private createFromForm(): IBillingInformation {
    return {
      ...new BillingInformation(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      taxnumber: this.editForm.get(['taxnumber'])!.value,
      billingAddressId: this.editForm.get(['billingAddressId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBillingInformation>>): void {
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

  trackById(index: number, item: IAddress): any {
    return item.id;
  }
}

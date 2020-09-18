import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBillingInformation } from 'app/shared/model/billing-information.model';

@Component({
  selector: 'jhi-billing-information-detail',
  templateUrl: './billing-information-detail.component.html',
})
export class BillingInformationDetailComponent implements OnInit {
  billingInformation: IBillingInformation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ billingInformation }) => (this.billingInformation = billingInformation));
  }

  previousState(): void {
    window.history.back();
  }
}

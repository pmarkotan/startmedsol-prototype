import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { BillingInformationComponent } from './billing-information.component';
import { BillingInformationDetailComponent } from './billing-information-detail.component';
import { BillingInformationUpdateComponent } from './billing-information-update.component';
import { BillingInformationDeleteDialogComponent } from './billing-information-delete-dialog.component';
import { billingInformationRoute } from './billing-information.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(billingInformationRoute)],
  declarations: [
    BillingInformationComponent,
    BillingInformationDetailComponent,
    BillingInformationUpdateComponent,
    BillingInformationDeleteDialogComponent,
  ],
  entryComponents: [BillingInformationDeleteDialogComponent],
})
export class StartMedsolPrototypeBillingInformationModule {}

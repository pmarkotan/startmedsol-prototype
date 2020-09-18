import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { MedicalInvoiceComponent } from './medical-invoice.component';
import { MedicalInvoiceDetailComponent } from './medical-invoice-detail.component';
import { MedicalInvoiceUpdateComponent } from './medical-invoice-update.component';
import { MedicalInvoiceDeleteDialogComponent } from './medical-invoice-delete-dialog.component';
import { medicalInvoiceRoute } from './medical-invoice.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(medicalInvoiceRoute)],
  declarations: [
    MedicalInvoiceComponent,
    MedicalInvoiceDetailComponent,
    MedicalInvoiceUpdateComponent,
    MedicalInvoiceDeleteDialogComponent,
  ],
  entryComponents: [MedicalInvoiceDeleteDialogComponent],
})
export class StartMedsolPrototypeMedicalInvoiceModule {}

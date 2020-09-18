import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { PphMedicineComponent } from './pph-medicine.component';
import { PphMedicineDetailComponent } from './pph-medicine-detail.component';
import { PphMedicineUpdateComponent } from './pph-medicine-update.component';
import { PphMedicineDeleteDialogComponent } from './pph-medicine-delete-dialog.component';
import { pphMedicineRoute } from './pph-medicine.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(pphMedicineRoute)],
  declarations: [PphMedicineComponent, PphMedicineDetailComponent, PphMedicineUpdateComponent, PphMedicineDeleteDialogComponent],
  entryComponents: [PphMedicineDeleteDialogComponent],
})
export class StartMedsolPrototypePphMedicineModule {}

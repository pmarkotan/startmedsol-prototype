import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { PphMedicineQualifiedNameComponent } from './pph-medicine-qualified-name.component';
import { PphMedicineQualifiedNameDetailComponent } from './pph-medicine-qualified-name-detail.component';
import { PphMedicineQualifiedNameUpdateComponent } from './pph-medicine-qualified-name-update.component';
import { PphMedicineQualifiedNameDeleteDialogComponent } from './pph-medicine-qualified-name-delete-dialog.component';
import { pphMedicineQualifiedNameRoute } from './pph-medicine-qualified-name.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(pphMedicineQualifiedNameRoute)],
  declarations: [
    PphMedicineQualifiedNameComponent,
    PphMedicineQualifiedNameDetailComponent,
    PphMedicineQualifiedNameUpdateComponent,
    PphMedicineQualifiedNameDeleteDialogComponent,
  ],
  entryComponents: [PphMedicineQualifiedNameDeleteDialogComponent],
})
export class StartMedsolPrototypePphMedicineQualifiedNameModule {}

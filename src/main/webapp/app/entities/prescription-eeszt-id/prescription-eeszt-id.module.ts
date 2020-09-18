import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { PrescriptionEesztIdComponent } from './prescription-eeszt-id.component';
import { PrescriptionEesztIdDetailComponent } from './prescription-eeszt-id-detail.component';
import { PrescriptionEesztIdUpdateComponent } from './prescription-eeszt-id-update.component';
import { PrescriptionEesztIdDeleteDialogComponent } from './prescription-eeszt-id-delete-dialog.component';
import { prescriptionEesztIdRoute } from './prescription-eeszt-id.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(prescriptionEesztIdRoute)],
  declarations: [
    PrescriptionEesztIdComponent,
    PrescriptionEesztIdDetailComponent,
    PrescriptionEesztIdUpdateComponent,
    PrescriptionEesztIdDeleteDialogComponent,
  ],
  entryComponents: [PrescriptionEesztIdDeleteDialogComponent],
})
export class StartMedsolPrototypePrescriptionEesztIdModule {}

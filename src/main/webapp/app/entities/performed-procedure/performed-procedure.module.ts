import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { PerformedProcedureComponent } from './performed-procedure.component';
import { PerformedProcedureDetailComponent } from './performed-procedure-detail.component';
import { PerformedProcedureUpdateComponent } from './performed-procedure-update.component';
import { PerformedProcedureDeleteDialogComponent } from './performed-procedure-delete-dialog.component';
import { performedProcedureRoute } from './performed-procedure.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(performedProcedureRoute)],
  declarations: [
    PerformedProcedureComponent,
    PerformedProcedureDetailComponent,
    PerformedProcedureUpdateComponent,
    PerformedProcedureDeleteDialogComponent,
  ],
  entryComponents: [PerformedProcedureDeleteDialogComponent],
})
export class StartMedsolPrototypePerformedProcedureModule {}

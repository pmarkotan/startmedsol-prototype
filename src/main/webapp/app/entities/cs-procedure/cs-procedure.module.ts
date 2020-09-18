import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { CsProcedureComponent } from './cs-procedure.component';
import { CsProcedureDetailComponent } from './cs-procedure-detail.component';
import { CsProcedureUpdateComponent } from './cs-procedure-update.component';
import { CsProcedureDeleteDialogComponent } from './cs-procedure-delete-dialog.component';
import { csProcedureRoute } from './cs-procedure.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(csProcedureRoute)],
  declarations: [CsProcedureComponent, CsProcedureDetailComponent, CsProcedureUpdateComponent, CsProcedureDeleteDialogComponent],
  entryComponents: [CsProcedureDeleteDialogComponent],
})
export class StartMedsolPrototypeCsProcedureModule {}

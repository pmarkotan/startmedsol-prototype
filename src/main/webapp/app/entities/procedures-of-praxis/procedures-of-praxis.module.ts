import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { ProceduresOfPraxisComponent } from './procedures-of-praxis.component';
import { ProceduresOfPraxisDetailComponent } from './procedures-of-praxis-detail.component';
import { ProceduresOfPraxisUpdateComponent } from './procedures-of-praxis-update.component';
import { ProceduresOfPraxisDeleteDialogComponent } from './procedures-of-praxis-delete-dialog.component';
import { proceduresOfPraxisRoute } from './procedures-of-praxis.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(proceduresOfPraxisRoute)],
  declarations: [
    ProceduresOfPraxisComponent,
    ProceduresOfPraxisDetailComponent,
    ProceduresOfPraxisUpdateComponent,
    ProceduresOfPraxisDeleteDialogComponent,
  ],
  entryComponents: [ProceduresOfPraxisDeleteDialogComponent],
})
export class StartMedsolPrototypeProceduresOfPraxisModule {}

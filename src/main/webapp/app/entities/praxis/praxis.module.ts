import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { PraxisComponent } from './praxis.component';
import { PraxisDetailComponent } from './praxis-detail.component';
import { PraxisUpdateComponent } from './praxis-update.component';
import { PraxisDeleteDialogComponent } from './praxis-delete-dialog.component';
import { praxisRoute } from './praxis.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(praxisRoute)],
  declarations: [PraxisComponent, PraxisDetailComponent, PraxisUpdateComponent, PraxisDeleteDialogComponent],
  entryComponents: [PraxisDeleteDialogComponent],
})
export class StartMedsolPrototypePraxisModule {}

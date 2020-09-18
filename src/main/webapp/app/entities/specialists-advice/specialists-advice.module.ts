import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { SpecialistsAdviceComponent } from './specialists-advice.component';
import { SpecialistsAdviceDetailComponent } from './specialists-advice-detail.component';
import { SpecialistsAdviceUpdateComponent } from './specialists-advice-update.component';
import { SpecialistsAdviceDeleteDialogComponent } from './specialists-advice-delete-dialog.component';
import { specialistsAdviceRoute } from './specialists-advice.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(specialistsAdviceRoute)],
  declarations: [
    SpecialistsAdviceComponent,
    SpecialistsAdviceDetailComponent,
    SpecialistsAdviceUpdateComponent,
    SpecialistsAdviceDeleteDialogComponent,
  ],
  entryComponents: [SpecialistsAdviceDeleteDialogComponent],
})
export class StartMedsolPrototypeSpecialistsAdviceModule {}

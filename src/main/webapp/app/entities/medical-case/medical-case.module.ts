import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { MedicalCaseComponent } from './medical-case.component';
import { MedicalCaseDetailComponent } from './medical-case-detail.component';
import { MedicalCaseUpdateComponent } from './medical-case-update.component';
import { MedicalCaseDeleteDialogComponent } from './medical-case-delete-dialog.component';
import { medicalCaseRoute } from './medical-case.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(medicalCaseRoute)],
  declarations: [MedicalCaseComponent, MedicalCaseDetailComponent, MedicalCaseUpdateComponent, MedicalCaseDeleteDialogComponent],
  entryComponents: [MedicalCaseDeleteDialogComponent],
})
export class StartMedsolPrototypeMedicalCaseModule {}

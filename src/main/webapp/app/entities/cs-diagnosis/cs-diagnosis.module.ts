import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { CsDiagnosisComponent } from './cs-diagnosis.component';
import { CsDiagnosisDetailComponent } from './cs-diagnosis-detail.component';
import { CsDiagnosisUpdateComponent } from './cs-diagnosis-update.component';
import { CsDiagnosisDeleteDialogComponent } from './cs-diagnosis-delete-dialog.component';
import { csDiagnosisRoute } from './cs-diagnosis.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(csDiagnosisRoute)],
  declarations: [CsDiagnosisComponent, CsDiagnosisDetailComponent, CsDiagnosisUpdateComponent, CsDiagnosisDeleteDialogComponent],
  entryComponents: [CsDiagnosisDeleteDialogComponent],
})
export class StartMedsolPrototypeCsDiagnosisModule {}

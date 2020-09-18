import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { MedicalCaseDiagnosisComponent } from './medical-case-diagnosis.component';
import { MedicalCaseDiagnosisDetailComponent } from './medical-case-diagnosis-detail.component';
import { MedicalCaseDiagnosisUpdateComponent } from './medical-case-diagnosis-update.component';
import { MedicalCaseDiagnosisDeleteDialogComponent } from './medical-case-diagnosis-delete-dialog.component';
import { medicalCaseDiagnosisRoute } from './medical-case-diagnosis.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(medicalCaseDiagnosisRoute)],
  declarations: [
    MedicalCaseDiagnosisComponent,
    MedicalCaseDiagnosisDetailComponent,
    MedicalCaseDiagnosisUpdateComponent,
    MedicalCaseDiagnosisDeleteDialogComponent,
  ],
  entryComponents: [MedicalCaseDiagnosisDeleteDialogComponent],
})
export class StartMedsolPrototypeMedicalCaseDiagnosisModule {}

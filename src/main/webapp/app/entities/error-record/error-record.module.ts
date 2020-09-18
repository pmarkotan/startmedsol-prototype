import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { ErrorRecordComponent } from './error-record.component';
import { ErrorRecordDetailComponent } from './error-record-detail.component';
import { ErrorRecordUpdateComponent } from './error-record-update.component';
import { ErrorRecordDeleteDialogComponent } from './error-record-delete-dialog.component';
import { errorRecordRoute } from './error-record.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(errorRecordRoute)],
  declarations: [ErrorRecordComponent, ErrorRecordDetailComponent, ErrorRecordUpdateComponent, ErrorRecordDeleteDialogComponent],
  entryComponents: [ErrorRecordDeleteDialogComponent],
})
export class StartMedsolPrototypeErrorRecordModule {}

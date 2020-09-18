import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { EhrDocumentComponent } from './ehr-document.component';
import { EhrDocumentDetailComponent } from './ehr-document-detail.component';
import { EhrDocumentUpdateComponent } from './ehr-document-update.component';
import { EhrDocumentDeleteDialogComponent } from './ehr-document-delete-dialog.component';
import { ehrDocumentRoute } from './ehr-document.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(ehrDocumentRoute)],
  declarations: [EhrDocumentComponent, EhrDocumentDetailComponent, EhrDocumentUpdateComponent, EhrDocumentDeleteDialogComponent],
  entryComponents: [EhrDocumentDeleteDialogComponent],
})
export class StartMedsolPrototypeEhrDocumentModule {}

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { ExternalDocumentComponent } from './external-document.component';
import { ExternalDocumentDetailComponent } from './external-document-detail.component';
import { ExternalDocumentUpdateComponent } from './external-document-update.component';
import { ExternalDocumentDeleteDialogComponent } from './external-document-delete-dialog.component';
import { externalDocumentRoute } from './external-document.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(externalDocumentRoute)],
  declarations: [
    ExternalDocumentComponent,
    ExternalDocumentDetailComponent,
    ExternalDocumentUpdateComponent,
    ExternalDocumentDeleteDialogComponent,
  ],
  entryComponents: [ExternalDocumentDeleteDialogComponent],
})
export class StartMedsolPrototypeExternalDocumentModule {}

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { CaseTextDocumentationComponent } from './case-text-documentation.component';
import { CaseTextDocumentationDetailComponent } from './case-text-documentation-detail.component';
import { CaseTextDocumentationUpdateComponent } from './case-text-documentation-update.component';
import { CaseTextDocumentationDeleteDialogComponent } from './case-text-documentation-delete-dialog.component';
import { caseTextDocumentationRoute } from './case-text-documentation.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(caseTextDocumentationRoute)],
  declarations: [
    CaseTextDocumentationComponent,
    CaseTextDocumentationDetailComponent,
    CaseTextDocumentationUpdateComponent,
    CaseTextDocumentationDeleteDialogComponent,
  ],
  entryComponents: [CaseTextDocumentationDeleteDialogComponent],
})
export class StartMedsolPrototypeCaseTextDocumentationModule {}

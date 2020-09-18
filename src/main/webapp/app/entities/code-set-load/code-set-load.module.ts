import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { CodeSetLoadComponent } from './code-set-load.component';
import { CodeSetLoadDetailComponent } from './code-set-load-detail.component';
import { CodeSetLoadUpdateComponent } from './code-set-load-update.component';
import { CodeSetLoadDeleteDialogComponent } from './code-set-load-delete-dialog.component';
import { codeSetLoadRoute } from './code-set-load.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(codeSetLoadRoute)],
  declarations: [CodeSetLoadComponent, CodeSetLoadDetailComponent, CodeSetLoadUpdateComponent, CodeSetLoadDeleteDialogComponent],
  entryComponents: [CodeSetLoadDeleteDialogComponent],
})
export class StartMedsolPrototypeCodeSetLoadModule {}

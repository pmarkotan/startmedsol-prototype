import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { DynamicFormComponent } from './dynamic-form.component';
import { DynamicFormDetailComponent } from './dynamic-form-detail.component';
import { DynamicFormUpdateComponent } from './dynamic-form-update.component';
import { DynamicFormDeleteDialogComponent } from './dynamic-form-delete-dialog.component';
import { dynamicFormRoute } from './dynamic-form.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(dynamicFormRoute)],
  declarations: [DynamicFormComponent, DynamicFormDetailComponent, DynamicFormUpdateComponent, DynamicFormDeleteDialogComponent],
  entryComponents: [DynamicFormDeleteDialogComponent],
})
export class StartMedsolPrototypeDynamicFormModule {}

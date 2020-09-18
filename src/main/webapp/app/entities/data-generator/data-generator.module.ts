import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { DataGeneratorComponent } from './data-generator.component';
import { DataGeneratorDetailComponent } from './data-generator-detail.component';
import { DataGeneratorUpdateComponent } from './data-generator-update.component';
import { DataGeneratorDeleteDialogComponent } from './data-generator-delete-dialog.component';
import { dataGeneratorRoute } from './data-generator.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(dataGeneratorRoute)],
  declarations: [DataGeneratorComponent, DataGeneratorDetailComponent, DataGeneratorUpdateComponent, DataGeneratorDeleteDialogComponent],
  entryComponents: [DataGeneratorDeleteDialogComponent],
})
export class StartMedsolPrototypeDataGeneratorModule {}

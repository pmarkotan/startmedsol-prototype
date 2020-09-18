import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { PuphaLoaderComponent } from './pupha-loader.component';
import { PuphaLoaderDetailComponent } from './pupha-loader-detail.component';
import { PuphaLoaderUpdateComponent } from './pupha-loader-update.component';
import { PuphaLoaderDeleteDialogComponent } from './pupha-loader-delete-dialog.component';
import { puphaLoaderRoute } from './pupha-loader.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(puphaLoaderRoute)],
  declarations: [PuphaLoaderComponent, PuphaLoaderDetailComponent, PuphaLoaderUpdateComponent, PuphaLoaderDeleteDialogComponent],
  entryComponents: [PuphaLoaderDeleteDialogComponent],
})
export class StartMedsolPrototypePuphaLoaderModule {}

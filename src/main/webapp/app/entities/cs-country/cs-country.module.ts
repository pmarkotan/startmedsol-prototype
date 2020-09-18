import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { CsCountryComponent } from './cs-country.component';
import { CsCountryDetailComponent } from './cs-country-detail.component';
import { CsCountryUpdateComponent } from './cs-country-update.component';
import { CsCountryDeleteDialogComponent } from './cs-country-delete-dialog.component';
import { csCountryRoute } from './cs-country.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(csCountryRoute)],
  declarations: [CsCountryComponent, CsCountryDetailComponent, CsCountryUpdateComponent, CsCountryDeleteDialogComponent],
  entryComponents: [CsCountryDeleteDialogComponent],
})
export class StartMedsolPrototypeCsCountryModule {}

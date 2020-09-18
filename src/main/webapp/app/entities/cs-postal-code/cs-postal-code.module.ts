import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { CsPostalCodeComponent } from './cs-postal-code.component';
import { CsPostalCodeDetailComponent } from './cs-postal-code-detail.component';
import { CsPostalCodeUpdateComponent } from './cs-postal-code-update.component';
import { CsPostalCodeDeleteDialogComponent } from './cs-postal-code-delete-dialog.component';
import { csPostalCodeRoute } from './cs-postal-code.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(csPostalCodeRoute)],
  declarations: [CsPostalCodeComponent, CsPostalCodeDetailComponent, CsPostalCodeUpdateComponent, CsPostalCodeDeleteDialogComponent],
  entryComponents: [CsPostalCodeDeleteDialogComponent],
})
export class StartMedsolPrototypeCsPostalCodeModule {}

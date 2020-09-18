import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { ContactPersonComponent } from './contact-person.component';
import { ContactPersonDetailComponent } from './contact-person-detail.component';
import { ContactPersonUpdateComponent } from './contact-person-update.component';
import { ContactPersonDeleteDialogComponent } from './contact-person-delete-dialog.component';
import { contactPersonRoute } from './contact-person.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(contactPersonRoute)],
  declarations: [ContactPersonComponent, ContactPersonDetailComponent, ContactPersonUpdateComponent, ContactPersonDeleteDialogComponent],
  entryComponents: [ContactPersonDeleteDialogComponent],
})
export class StartMedsolPrototypeContactPersonModule {}

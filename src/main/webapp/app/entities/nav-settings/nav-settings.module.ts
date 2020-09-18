import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { NavSettingsComponent } from './nav-settings.component';
import { NavSettingsDetailComponent } from './nav-settings-detail.component';
import { NavSettingsUpdateComponent } from './nav-settings-update.component';
import { NavSettingsDeleteDialogComponent } from './nav-settings-delete-dialog.component';
import { navSettingsRoute } from './nav-settings.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(navSettingsRoute)],
  declarations: [NavSettingsComponent, NavSettingsDetailComponent, NavSettingsUpdateComponent, NavSettingsDeleteDialogComponent],
  entryComponents: [NavSettingsDeleteDialogComponent],
})
export class StartMedsolPrototypeNavSettingsModule {}

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { ProviderComponent } from './provider.component';
import { ProviderDetailComponent } from './provider-detail.component';
import { ProviderUpdateComponent } from './provider-update.component';
import { ProviderDeleteDialogComponent } from './provider-delete-dialog.component';
import { providerRoute } from './provider.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(providerRoute)],
  declarations: [ProviderComponent, ProviderDetailComponent, ProviderUpdateComponent, ProviderDeleteDialogComponent],
  entryComponents: [ProviderDeleteDialogComponent],
})
export class StartMedsolPrototypeProviderModule {}

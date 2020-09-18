import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { DictionaryItemComponent } from './dictionary-item.component';
import { DictionaryItemDetailComponent } from './dictionary-item-detail.component';
import { DictionaryItemUpdateComponent } from './dictionary-item-update.component';
import { DictionaryItemDeleteDialogComponent } from './dictionary-item-delete-dialog.component';
import { dictionaryItemRoute } from './dictionary-item.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(dictionaryItemRoute)],
  declarations: [
    DictionaryItemComponent,
    DictionaryItemDetailComponent,
    DictionaryItemUpdateComponent,
    DictionaryItemDeleteDialogComponent,
  ],
  entryComponents: [DictionaryItemDeleteDialogComponent],
})
export class StartMedsolPrototypeDictionaryItemModule {}

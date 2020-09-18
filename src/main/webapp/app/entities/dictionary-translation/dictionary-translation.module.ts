import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { DictionaryTranslationComponent } from './dictionary-translation.component';
import { DictionaryTranslationDetailComponent } from './dictionary-translation-detail.component';
import { DictionaryTranslationUpdateComponent } from './dictionary-translation-update.component';
import { DictionaryTranslationDeleteDialogComponent } from './dictionary-translation-delete-dialog.component';
import { dictionaryTranslationRoute } from './dictionary-translation.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(dictionaryTranslationRoute)],
  declarations: [
    DictionaryTranslationComponent,
    DictionaryTranslationDetailComponent,
    DictionaryTranslationUpdateComponent,
    DictionaryTranslationDeleteDialogComponent,
  ],
  entryComponents: [DictionaryTranslationDeleteDialogComponent],
})
export class StartMedsolPrototypeDictionaryTranslationModule {}

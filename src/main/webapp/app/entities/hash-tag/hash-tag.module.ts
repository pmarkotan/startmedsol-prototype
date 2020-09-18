import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { HashTagComponent } from './hash-tag.component';
import { HashTagDetailComponent } from './hash-tag-detail.component';
import { HashTagUpdateComponent } from './hash-tag-update.component';
import { HashTagDeleteDialogComponent } from './hash-tag-delete-dialog.component';
import { hashTagRoute } from './hash-tag.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(hashTagRoute)],
  declarations: [HashTagComponent, HashTagDetailComponent, HashTagUpdateComponent, HashTagDeleteDialogComponent],
  entryComponents: [HashTagDeleteDialogComponent],
})
export class StartMedsolPrototypeHashTagModule {}

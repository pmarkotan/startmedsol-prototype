import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { FeedBackMessageComponent } from './feed-back-message.component';
import { FeedBackMessageDetailComponent } from './feed-back-message-detail.component';
import { FeedBackMessageUpdateComponent } from './feed-back-message-update.component';
import { FeedBackMessageDeleteDialogComponent } from './feed-back-message-delete-dialog.component';
import { feedBackMessageRoute } from './feed-back-message.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(feedBackMessageRoute)],
  declarations: [
    FeedBackMessageComponent,
    FeedBackMessageDetailComponent,
    FeedBackMessageUpdateComponent,
    FeedBackMessageDeleteDialogComponent,
  ],
  entryComponents: [FeedBackMessageDeleteDialogComponent],
})
export class StartMedsolPrototypeFeedBackMessageModule {}

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StartMedsolPrototypeSharedModule } from 'app/shared/shared.module';
import { StatisticsComponent } from './statistics.component';
import { StatisticsDetailComponent } from './statistics-detail.component';
import { StatisticsUpdateComponent } from './statistics-update.component';
import { StatisticsDeleteDialogComponent } from './statistics-delete-dialog.component';
import { statisticsRoute } from './statistics.route';

@NgModule({
  imports: [StartMedsolPrototypeSharedModule, RouterModule.forChild(statisticsRoute)],
  declarations: [StatisticsComponent, StatisticsDetailComponent, StatisticsUpdateComponent, StatisticsDeleteDialogComponent],
  entryComponents: [StatisticsDeleteDialogComponent],
})
export class StartMedsolPrototypeStatisticsModule {}

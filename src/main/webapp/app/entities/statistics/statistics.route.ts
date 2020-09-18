import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IStatistics, Statistics } from 'app/shared/model/statistics.model';
import { StatisticsService } from './statistics.service';
import { StatisticsComponent } from './statistics.component';
import { StatisticsDetailComponent } from './statistics-detail.component';
import { StatisticsUpdateComponent } from './statistics-update.component';

@Injectable({ providedIn: 'root' })
export class StatisticsResolve implements Resolve<IStatistics> {
  constructor(private service: StatisticsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStatistics> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((statistics: HttpResponse<Statistics>) => {
          if (statistics.body) {
            return of(statistics.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Statistics());
  }
}

export const statisticsRoute: Routes = [
  {
    path: '',
    component: StatisticsComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.statistics.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StatisticsDetailComponent,
    resolve: {
      statistics: StatisticsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.statistics.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StatisticsUpdateComponent,
    resolve: {
      statistics: StatisticsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.statistics.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StatisticsUpdateComponent,
    resolve: {
      statistics: StatisticsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.statistics.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

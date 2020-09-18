import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPerformedProcedure, PerformedProcedure } from 'app/shared/model/performed-procedure.model';
import { PerformedProcedureService } from './performed-procedure.service';
import { PerformedProcedureComponent } from './performed-procedure.component';
import { PerformedProcedureDetailComponent } from './performed-procedure-detail.component';
import { PerformedProcedureUpdateComponent } from './performed-procedure-update.component';

@Injectable({ providedIn: 'root' })
export class PerformedProcedureResolve implements Resolve<IPerformedProcedure> {
  constructor(private service: PerformedProcedureService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPerformedProcedure> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((performedProcedure: HttpResponse<PerformedProcedure>) => {
          if (performedProcedure.body) {
            return of(performedProcedure.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PerformedProcedure());
  }
}

export const performedProcedureRoute: Routes = [
  {
    path: '',
    component: PerformedProcedureComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.performedProcedure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PerformedProcedureDetailComponent,
    resolve: {
      performedProcedure: PerformedProcedureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.performedProcedure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PerformedProcedureUpdateComponent,
    resolve: {
      performedProcedure: PerformedProcedureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.performedProcedure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PerformedProcedureUpdateComponent,
    resolve: {
      performedProcedure: PerformedProcedureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.performedProcedure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

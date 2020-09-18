import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProceduresOfPraxis, ProceduresOfPraxis } from 'app/shared/model/procedures-of-praxis.model';
import { ProceduresOfPraxisService } from './procedures-of-praxis.service';
import { ProceduresOfPraxisComponent } from './procedures-of-praxis.component';
import { ProceduresOfPraxisDetailComponent } from './procedures-of-praxis-detail.component';
import { ProceduresOfPraxisUpdateComponent } from './procedures-of-praxis-update.component';

@Injectable({ providedIn: 'root' })
export class ProceduresOfPraxisResolve implements Resolve<IProceduresOfPraxis> {
  constructor(private service: ProceduresOfPraxisService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProceduresOfPraxis> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((proceduresOfPraxis: HttpResponse<ProceduresOfPraxis>) => {
          if (proceduresOfPraxis.body) {
            return of(proceduresOfPraxis.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProceduresOfPraxis());
  }
}

export const proceduresOfPraxisRoute: Routes = [
  {
    path: '',
    component: ProceduresOfPraxisComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.proceduresOfPraxis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProceduresOfPraxisDetailComponent,
    resolve: {
      proceduresOfPraxis: ProceduresOfPraxisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.proceduresOfPraxis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProceduresOfPraxisUpdateComponent,
    resolve: {
      proceduresOfPraxis: ProceduresOfPraxisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.proceduresOfPraxis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProceduresOfPraxisUpdateComponent,
    resolve: {
      proceduresOfPraxis: ProceduresOfPraxisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.proceduresOfPraxis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPraxis, Praxis } from 'app/shared/model/praxis.model';
import { PraxisService } from './praxis.service';
import { PraxisComponent } from './praxis.component';
import { PraxisDetailComponent } from './praxis-detail.component';
import { PraxisUpdateComponent } from './praxis-update.component';

@Injectable({ providedIn: 'root' })
export class PraxisResolve implements Resolve<IPraxis> {
  constructor(private service: PraxisService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPraxis> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((praxis: HttpResponse<Praxis>) => {
          if (praxis.body) {
            return of(praxis.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Praxis());
  }
}

export const praxisRoute: Routes = [
  {
    path: '',
    component: PraxisComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.praxis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PraxisDetailComponent,
    resolve: {
      praxis: PraxisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.praxis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PraxisUpdateComponent,
    resolve: {
      praxis: PraxisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.praxis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PraxisUpdateComponent,
    resolve: {
      praxis: PraxisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.praxis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

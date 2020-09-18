import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICsProcedure, CsProcedure } from 'app/shared/model/cs-procedure.model';
import { CsProcedureService } from './cs-procedure.service';
import { CsProcedureComponent } from './cs-procedure.component';
import { CsProcedureDetailComponent } from './cs-procedure-detail.component';
import { CsProcedureUpdateComponent } from './cs-procedure-update.component';

@Injectable({ providedIn: 'root' })
export class CsProcedureResolve implements Resolve<ICsProcedure> {
  constructor(private service: CsProcedureService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICsProcedure> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((csProcedure: HttpResponse<CsProcedure>) => {
          if (csProcedure.body) {
            return of(csProcedure.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CsProcedure());
  }
}

export const csProcedureRoute: Routes = [
  {
    path: '',
    component: CsProcedureComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.csProcedure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CsProcedureDetailComponent,
    resolve: {
      csProcedure: CsProcedureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csProcedure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CsProcedureUpdateComponent,
    resolve: {
      csProcedure: CsProcedureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csProcedure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CsProcedureUpdateComponent,
    resolve: {
      csProcedure: CsProcedureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csProcedure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICsPostalCode, CsPostalCode } from 'app/shared/model/cs-postal-code.model';
import { CsPostalCodeService } from './cs-postal-code.service';
import { CsPostalCodeComponent } from './cs-postal-code.component';
import { CsPostalCodeDetailComponent } from './cs-postal-code-detail.component';
import { CsPostalCodeUpdateComponent } from './cs-postal-code-update.component';

@Injectable({ providedIn: 'root' })
export class CsPostalCodeResolve implements Resolve<ICsPostalCode> {
  constructor(private service: CsPostalCodeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICsPostalCode> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((csPostalCode: HttpResponse<CsPostalCode>) => {
          if (csPostalCode.body) {
            return of(csPostalCode.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CsPostalCode());
  }
}

export const csPostalCodeRoute: Routes = [
  {
    path: '',
    component: CsPostalCodeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csPostalCode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CsPostalCodeDetailComponent,
    resolve: {
      csPostalCode: CsPostalCodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csPostalCode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CsPostalCodeUpdateComponent,
    resolve: {
      csPostalCode: CsPostalCodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csPostalCode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CsPostalCodeUpdateComponent,
    resolve: {
      csPostalCode: CsPostalCodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csPostalCode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

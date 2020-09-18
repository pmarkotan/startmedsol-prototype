import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICsCountry, CsCountry } from 'app/shared/model/cs-country.model';
import { CsCountryService } from './cs-country.service';
import { CsCountryComponent } from './cs-country.component';
import { CsCountryDetailComponent } from './cs-country-detail.component';
import { CsCountryUpdateComponent } from './cs-country-update.component';

@Injectable({ providedIn: 'root' })
export class CsCountryResolve implements Resolve<ICsCountry> {
  constructor(private service: CsCountryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICsCountry> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((csCountry: HttpResponse<CsCountry>) => {
          if (csCountry.body) {
            return of(csCountry.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CsCountry());
  }
}

export const csCountryRoute: Routes = [
  {
    path: '',
    component: CsCountryComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csCountry.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CsCountryDetailComponent,
    resolve: {
      csCountry: CsCountryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csCountry.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CsCountryUpdateComponent,
    resolve: {
      csCountry: CsCountryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csCountry.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CsCountryUpdateComponent,
    resolve: {
      csCountry: CsCountryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csCountry.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

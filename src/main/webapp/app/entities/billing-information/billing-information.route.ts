import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBillingInformation, BillingInformation } from 'app/shared/model/billing-information.model';
import { BillingInformationService } from './billing-information.service';
import { BillingInformationComponent } from './billing-information.component';
import { BillingInformationDetailComponent } from './billing-information-detail.component';
import { BillingInformationUpdateComponent } from './billing-information-update.component';

@Injectable({ providedIn: 'root' })
export class BillingInformationResolve implements Resolve<IBillingInformation> {
  constructor(private service: BillingInformationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBillingInformation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((billingInformation: HttpResponse<BillingInformation>) => {
          if (billingInformation.body) {
            return of(billingInformation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BillingInformation());
  }
}

export const billingInformationRoute: Routes = [
  {
    path: '',
    component: BillingInformationComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.billingInformation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BillingInformationDetailComponent,
    resolve: {
      billingInformation: BillingInformationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.billingInformation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BillingInformationUpdateComponent,
    resolve: {
      billingInformation: BillingInformationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.billingInformation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BillingInformationUpdateComponent,
    resolve: {
      billingInformation: BillingInformationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.billingInformation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

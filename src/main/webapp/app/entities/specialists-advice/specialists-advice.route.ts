import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISpecialistsAdvice, SpecialistsAdvice } from 'app/shared/model/specialists-advice.model';
import { SpecialistsAdviceService } from './specialists-advice.service';
import { SpecialistsAdviceComponent } from './specialists-advice.component';
import { SpecialistsAdviceDetailComponent } from './specialists-advice-detail.component';
import { SpecialistsAdviceUpdateComponent } from './specialists-advice-update.component';

@Injectable({ providedIn: 'root' })
export class SpecialistsAdviceResolve implements Resolve<ISpecialistsAdvice> {
  constructor(private service: SpecialistsAdviceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISpecialistsAdvice> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((specialistsAdvice: HttpResponse<SpecialistsAdvice>) => {
          if (specialistsAdvice.body) {
            return of(specialistsAdvice.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SpecialistsAdvice());
  }
}

export const specialistsAdviceRoute: Routes = [
  {
    path: '',
    component: SpecialistsAdviceComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.specialistsAdvice.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SpecialistsAdviceDetailComponent,
    resolve: {
      specialistsAdvice: SpecialistsAdviceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.specialistsAdvice.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SpecialistsAdviceUpdateComponent,
    resolve: {
      specialistsAdvice: SpecialistsAdviceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.specialistsAdvice.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SpecialistsAdviceUpdateComponent,
    resolve: {
      specialistsAdvice: SpecialistsAdviceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.specialistsAdvice.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

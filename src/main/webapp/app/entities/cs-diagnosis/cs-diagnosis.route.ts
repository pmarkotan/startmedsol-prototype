import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICsDiagnosis, CsDiagnosis } from 'app/shared/model/cs-diagnosis.model';
import { CsDiagnosisService } from './cs-diagnosis.service';
import { CsDiagnosisComponent } from './cs-diagnosis.component';
import { CsDiagnosisDetailComponent } from './cs-diagnosis-detail.component';
import { CsDiagnosisUpdateComponent } from './cs-diagnosis-update.component';

@Injectable({ providedIn: 'root' })
export class CsDiagnosisResolve implements Resolve<ICsDiagnosis> {
  constructor(private service: CsDiagnosisService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICsDiagnosis> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((csDiagnosis: HttpResponse<CsDiagnosis>) => {
          if (csDiagnosis.body) {
            return of(csDiagnosis.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CsDiagnosis());
  }
}

export const csDiagnosisRoute: Routes = [
  {
    path: '',
    component: CsDiagnosisComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csDiagnosis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CsDiagnosisDetailComponent,
    resolve: {
      csDiagnosis: CsDiagnosisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csDiagnosis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CsDiagnosisUpdateComponent,
    resolve: {
      csDiagnosis: CsDiagnosisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csDiagnosis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CsDiagnosisUpdateComponent,
    resolve: {
      csDiagnosis: CsDiagnosisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.csDiagnosis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

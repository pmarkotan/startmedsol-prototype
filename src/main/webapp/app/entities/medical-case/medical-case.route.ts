import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMedicalCase, MedicalCase } from 'app/shared/model/medical-case.model';
import { MedicalCaseService } from './medical-case.service';
import { MedicalCaseComponent } from './medical-case.component';
import { MedicalCaseDetailComponent } from './medical-case-detail.component';
import { MedicalCaseUpdateComponent } from './medical-case-update.component';

@Injectable({ providedIn: 'root' })
export class MedicalCaseResolve implements Resolve<IMedicalCase> {
  constructor(private service: MedicalCaseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMedicalCase> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((medicalCase: HttpResponse<MedicalCase>) => {
          if (medicalCase.body) {
            return of(medicalCase.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MedicalCase());
  }
}

export const medicalCaseRoute: Routes = [
  {
    path: '',
    component: MedicalCaseComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.medicalCase.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MedicalCaseDetailComponent,
    resolve: {
      medicalCase: MedicalCaseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.medicalCase.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MedicalCaseUpdateComponent,
    resolve: {
      medicalCase: MedicalCaseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.medicalCase.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MedicalCaseUpdateComponent,
    resolve: {
      medicalCase: MedicalCaseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.medicalCase.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
